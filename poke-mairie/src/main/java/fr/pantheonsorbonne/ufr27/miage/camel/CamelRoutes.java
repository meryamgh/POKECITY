package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.camel.agregator.FightersAggregationStrategy;
import fr.pantheonsorbonne.ufr27.miage.camel.gateways.BankGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.gateways.DresseurGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.gateways.PokemonGateway;
import fr.pantheonsorbonne.ufr27.miage.exception.DresseurBannedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;


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
                .setBody(simple("Dresseur is banned"));



        from("sjms2:queue:" + jmsPrefix + "bankRoute")
                .setHeader("idDresseur", constant(idDresseur))
                .bean(bank, "checkBalance(${headers.price}, ${headers.idDresseur})")
                .choice()
                .when(simple("${headers.success}"))
                .toD("sjms2:queue:" + jmsPrefix + "${headers.source}?exchangePattern=InOut&requestTimeout=60000")
                .otherwise()
                .to("sjms2:queue:"+ jmsPrefix + ".pokestore-after-fight")


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
                .to("sjms2:queue:"+ jmsPrefix +".PokemonToSchool?exchangePattern=InOut")
                .unmarshal().json(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class)
                .log("body : ${body}, header : ${header.price}")
                .setHeader("idDresseur", constant(idDresseur))
                .bean(bank, "checkBalance(${headers.price}, ${headers.idDresseur})")
                .choice()
                .when(simple("${headers.success}"))
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'school')")
                .marshal().json()
                .to("sjms2:queue:"+ jmsPrefix +".BankResponse?exchangePattern=InOut&requestTimeout=60000")
                .bean(pokemonGateway, "improvePokemon(${body})")
                .log("improved pokemon : ${body}")
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'mairie')")
                .otherwise()
                .log("pas assez d'argent")

        ;

        from("sjms2:queue:"+ jmsPrefix +".newPokemon")
                .bean(pokemonGateway, "addNewPokemonFromStore")
                .setBody(simple("Welcome to the new Pokemon named ${body.name()} in our city !"))
                .to("sjms2:topic:"+ jmsPrefix + ".pokemonAddInOurCity")
        ;


        from("direct:fightingPokemon")
                .marshal().json()
                .to("sjms2:queue:"+ jmsPrefix + ".getPokemonForFight?exchangePattern=InOut")
                .log("the pokemon for fight ADVERSAIRE is ${body}")
                .setHeader("idDresseur", constant(idDresseur))
                .log("1 ${body}")
                .split(body(), new FightersAggregationStrategy())
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'fight')")
                .end()
                .log("iici pas d'erreur ${body}")
                .log("${body}")
                .to("sjms2:queue:"+ jmsPrefix + ".fight?exchangePattern=InOut&requestTimeout=60000")
                .log("arriver à la mairie after fight")
                .split(body())
                .choice()
                .when(simple("${body.isAdopted}"))
                .to("direct:pokemonAdopted")
                .otherwise()
                .log("go back to store je suis pokemon du store ${body}")
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'store')")
                .to("sjms2:queue:"+ jmsPrefix + ".returnPNJ")
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
                    .to("sjms2:queue:"+ jmsPrefix + ".soin?exchangePattern=InOut")
                    .log("Je vais me faire soigner peut etre")
                    .bean(bank, "checkBalance(${header.price}, ${headers.idDresseur})")
                .log("apres le check balance")
                    .choice()
                        .when(simple("${headers.success}"))
                        .bean(pokemonGateway, "setLocalisationPokemon(${body},'soin')")
                        .to("sjms2:queue:" + jmsPrefix + "pokeInfirmerie?exchangePattern=InOut&requestTimeout=60000")
                        .bean(pokemonGateway, "setLocalisationPokemon(${body},'mairie')")
                        .log("j'ai assez je vais me faire soigner")
                    .endChoice()
                    .otherwise()
                        .log("j'ai pas assez je vais pas me faire soigner")
                        .bean(dresseurGateway, "deletePokemonFromDresseurPokedex(${body},${headers.idDresseur})")
                        .bean(pokemonGateway, "setLocalisationPokemon(${body},'store')")
                        .to("sjms2:queue:"+ jmsPrefix + ".returnPNJ")
                        .bean(pokemonGateway, "isDresseurOutOfPokemons(${headers.idDresseur})")
                        .choice()
                            .when(simple("${headers.isLastPokemon}"))
                            .log("plus de pokemon ${body}")
                            .bean(dresseurGateway, "bannedDresseur(${headers.idDresseur})")
                            .setBody(simple("DRESSEUR WITH ID ${headers.idDresseur} IS BANNED"))
                            .to("sjms2:topic:"+ jmsPrefix + ".dresseurBanned")
                        .endChoice()
                        .otherwise()
                            .log("pas son dernier pokemon")
        ;


    }

}


