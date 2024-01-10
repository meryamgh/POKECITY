package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.camel.gateways.BankGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.gateways.DresseurGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.gateways.PokemonGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.Fighters;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.exception.DresseurBannedException;
import fr.pantheonsorbonne.ufr27.miage.exception.NotEnoughMoneyException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import java.util.List;


@ApplicationScoped
public class CamelRoutes extends RouteBuilder {


    @Inject
    BankGateway bank;

    @Inject
    PokemonGateway pokemonGateway;


    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.dresseurId")
    int idDresseur;

    @Inject
    DresseurGateway dresseurGateway;


    @Override
    public void configure() {

        onException(DresseurBannedException.class)
                .handled(true)
                .setHeader("success", simple("false"))
                .setBody(simple("No seat is available"));

        onException(NotEnoughMoneyException.class)
                .handled(true)
                .setHeader("success", simple("false"))
                .setBody(simple("Not enough money"));

        from("sjms2:queue:" + jmsPrefix + "bankRoute")
                .setHeader("idDresseur", constant(idDresseur))
                .bean(bank, "checkBalance(${headers.price}, ${headers.idDresseur})")
                .choice()
                .when(simple("${headers.success}"))
                .toD("sjms2:queue:" + jmsPrefix + "${headers.source}?exchangePattern=InOut&requestTimeout=60000")
                .otherwise()
                .to("sjms2:queue:M1.pokestore-after-fight")


        ;

        from("sjms2:queue:" + jmsPrefix + "pokemonBuyCheckPrice?exchangePattern=InOut")
                .unmarshal().json(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class)
                .setHeader("idDresseur", constant(idDresseur))
                .bean(dresseurGateway, "checkDresseurBanned(${headers.idDresseur})")
                .bean(bank, "checkBalance(${headers.price}, ${headers.idDresseur})")
                .marshal().json()
        ;

        from("sjms2:queue:" + jmsPrefix + "pokemonBuy")
                .unmarshal().json(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class)
                .bean(pokemonGateway, "affectPokemonToDresseur(${body},  ${headers.idDresseur})")
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'mairie')")
                .marshal().json();

        from("direct:sendPokemonToSchool")
                .routeId("sendPokemonToSchoolRoute")
                .log("Sending pokemon to school: ${body}")
                .marshal().json()
                .to("sjms2:queue:M1.PokemonToSchool?exchangePattern=InOut")
                .unmarshal().json(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class)
                .log("body : ${body}, header : ${header.price}")
                .setHeader("idDresseur", constant(idDresseur))
                .bean(bank, "checkBalance(${headers.price}, ${headers.idDresseur})")
                .choice()
                .when(simple("${headers.success}"))
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'school')")
                .marshal().json()
                .to("sjms2:queue:M1.BankResponse?exchangePattern=InOut&requestTimeout=60000")
                .bean(pokemonGateway, "improvePokemon(${body})")
                .log("improved pokemon : ${body}")
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'mairie')")
                .otherwise()
                .log("pas assez d'argent")

        ;

        from("sjms2:queue:M1.newPokemon")
                .bean(pokemonGateway, "addNewPokemonFromStore")
                .setBody(simple("Welcome to the new Pokemon named ${body.name()} in our city !"))
                .to("sjms2:topic:M1.pokemonAddInOurCity")
        ;


        from("direct:fightingPokemon")
                .marshal().json()
                .to("sjms2:queue:M1.getPokemonForFight?exchangePattern=InOut")
                .log("the pokemon for fight ADVERSAIRE is ${body}")
                .setHeader("idDresseur", constant(idDresseur))
                .log("1 ${body}")
                .split(body())
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'fight')")
                .end()
                .log("iici pas d'erreur ${body}")
                .process(new FighterProcessor())
                .log("${body}")
                .to("sjms2:queue:M1.fight?exchangePattern=InOut&requestTimeout=60000")
                .log("arriver à la mairie after fight")
                .split(body())
                .choice()
                .when(simple("${body.isAdopted}"))
                .to("direct:pokemonAdopted")
                .otherwise()
                .log("go back to store je suis pokemon du store ${body}")
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'store')")
                .to("sjms2:queue:M1.returnPNJ")
                .log("pokemon pas au dresseur ${body}")
        ;


        from("direct:pokemonAdopted")
                .log("je suis le pokemon du dresseur ${body}")
                .choice()
                .when(simple("${headers.isWinner}"))
                .bean(bank, "addAmountWinToBankAccount(${headers.amountWin},${headers.idDresseur})")
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'mairie')")
                .log("J'ai gagner ${headers.amountWin}")
                .endChoice()
                .otherwise()
                .log("perdu")
                .to("sjms2:queue:M1.soin?exchangePattern=InOut")
                .log("Je vais me faire soigner peut etre")
                .bean(bank, "checkBalance(${header.price}, ${headers.idDresseur})")
                .choice()
                .when(simple("${headers.success}"))
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'soin')")
                .toD("sjms2:queue:" + jmsPrefix + "pokeInfirmerie?exchangePattern=InOut&requestTimeout=60000")
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'mairie')")
                .log("j'ai assez je vais me faire soigner")
                .endChoice()
                .otherwise()
                .log("j'ai pas assez je vais pas me faire soigner")
                .bean(dresseurGateway, "deletePokemonFromDresseurPokedex(${body},${headers.idDresseur})")
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'store')")
                .to("sjms2:queue:M1.returnPNJ")
                .bean(pokemonGateway, "isDresseurOutOfPokemons(${headers.idDresseur})")
                .choice()
                .when(simple("${headers.isLastPokemon}"))
                .log("plus de pokemon ${body}")
                .bean(dresseurGateway, "bannedDresseur(${headers.idDresseur})")
                .setBody(simple("DRESSEUR WITH ID ${headers.idDresseur} IS BANNED"))
                .to("sjms2:topic:M1.dresseurBanned")
                .endChoice()
                .otherwise()
                .log("pas son dernier pokemon")
        ;


    }

    private static class FighterProcessor implements Processor {
        @Override
        public void process (Exchange exchange) {
            List<Pokemon> pokemons = (List<Pokemon>) exchange.getMessage().getBody();
            Pokemon ourPokemon;
            Pokemon PNJ;
            if (pokemons.get(0).isAdopted()) {
                ourPokemon = pokemons.get(0);
                PNJ = pokemons.get(1);
            } else {
                ourPokemon = pokemons.get(1);
                PNJ = pokemons.get(0);
            }
            Fighters fighters = new Fighters(PNJ, ourPokemon);
            exchange.getMessage().setBody(fighters);
        }
    }

}


