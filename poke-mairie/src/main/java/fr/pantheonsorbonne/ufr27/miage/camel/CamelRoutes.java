package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.FightSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.ArrayList;
import java.util.Collection;

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

    @Inject
    CamelContext camelContext;



    @Override
    public void configure() throws Exception {
        this.camelContext.setTracing(true);
        from("sjms2:queue:" + jmsPrefix +"bankRoute")
                .setHeader("idDresseur", constant(idDresseur))
                .bean(bank, "checkBalance(${headers.price}, ${headers.idDresseur})")
                .choice()
                .when(simple("${headers.responseHaveEnoughMoney}"))
                .toD("sjms2:queue:" + jmsPrefix + "${headers.source}?exchangePattern=InOut&requestTimeout=60000")
                .otherwise()
                .to("sjms2:queue:M1.pokestore-after-fight")



        ;

        from("sjms2:queue:"+ jmsPrefix + "pokemonBuyCheckPrice?exchangePattern=InOut")
                .unmarshal().json(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class)
                .setHeader("idDresseur", constant(idDresseur))
                .bean(bank, "checkBalance(${headers.price}, ${headers.idDresseur})")
                .marshal().json()
                ;

        from("sjms2:queue:"+ jmsPrefix + "pokemonBuy")
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
                .when(simple("${headers.responseHaveEnoughMoney}"))
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'school')")
                .marshal().json()
                .to("sjms2:queue:M1.BankResponse?exchangePattern=InOut&requestTimeout=60000")
                .bean(pokemonGateway, "improvePokemon(${body})")
                .log("improved pokemon : ${body}")
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'mairie')")
                .otherwise()
                .log("pas assez d'argent")

        ;


        from("direct:fightingPokemon")
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'fight')")
                .marshal().json()
                .to("sjms2:queue:M1.getPokemonForFight?exchangePattern=InOut")
                .log("the pokemon for fight ADVERSAIRE is ${body}")
                .split(body())
                .bean(pokemonGateway, "setLocalisationPokemon(${body},'fight')")
                .end()
                .setHeader("idDresseur", constant(idDresseur))
                .to("sjms2:queue:M1.fight?exchangePattern=InOut&requestTimeout=60000")
                .split(body())
                .choice()
                .when(simple("${body.isAdopted}"))
                    .log("je suis le pokemon du dresseur ${body}")
                    .choice()
                        .when(simple("${headers.isWinner}"))
                            .bean(bank, "addAmountWinToBankAccount(${headers.amountWin},${headers.idDresseur})")
                            .bean(pokemonGateway, "setLocalisationPokemon(${body},'mairie')" )
                            .log("J'ai gagner")

                        .otherwise()
                            .to("sjms2:queue:M1.soin?exchangePattern=InOut")
                            .log("Je vais me faire soigner peut etre")
                            .bean(bank, "checkBalance(${header.price}, ${headers.idDresseur})")
                            .choice()
                                .when(simple("${headers.responseHaveEnoughMoney}"))
                                    .bean(pokemonGateway, "setLocalisationPokemon(${body},'soin')")
                                    .toD("sjms2:queue:" + jmsPrefix + "pokeInfirmerie?exchangePattern=InOut&requestTimeout=60000")
                                    .bean(pokemonGateway, "setLocalisationPokemon(${body},'mairie')")
                                    .log("j'ai assez je vais me faire soigner")
                                .otherwise()
                                    .log("j'ai pas assez je vais pas me faire soigner")
                                    .bean(pokemonGateway, "checkLastPokemon(${headers.idDresseur})")
                                    .choice()
                                        .when(simple("${headers.isLastPokemon}"))
                                            .log("mon dernier pokemon ${body}")
                                            .bean(pokemonGateway, "setLocalisationPokemon(${body},'store')" )

                                            .to("sjms2:queue:M1.returnPNJ")

                                            .bean(dresseurGateway,"bannedDresseur(${headers.idDresseur})")
                                            .to("sjms2:topic:M1.dresseurBanned")

                                        .otherwise()
                                            .log("pas son dernier pokemon")

                .log("go back to store je suis pokemon du store ${body}")
                .choice()
                .when(simple("${body.isAdopted}"))
                 .bean(pokemonGateway, "setLocalisationPokemon(${body},'store')" )
                .to("sjms2:queue:M1.returnPNJ")
                .log("pokemon pas au dresseur ${body}")


        ;

    }

}


