package fr.pantheonsorbonne.ufr27.miage.camel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
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
                .marshal().json()

                .split(xpath("//ourPokemon"))
                //.unmarshal().json(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class)
                .log("apres vombat mairie ${body}")

              //  .unmarshal().json(Pokemon.class)
              //  .bean(fightGateway, "stockPokemonToStore(${body})")


//                .choice()
//                    .when(simple("${body.isWinner}"))
//                        .bean(bank, "addAmountWinToBankAccount(${body})")
//                        .bean(pokemonGateway, "setLocalisationPokemon(${body.ourPokemon()},'mairie')" )
//                        .log("setloca id pokemon dresseur")
//                    .endChoice() // Close the current choice block
//s
//                    .otherwise()
//                        .to("sjms2:queue:M1.soin?exchangePattern=InOut")
//                        .log("price treeatment")
//                        .bean(bank, "checkBalance(${header.price}, ${headers.idDresseur})")
//                        .choice()
//                            .when(simple("${headers.responseHaveEnoughMoney}"))
//                                .bean(pokemonGateway, "setLocalisationPokemon(${body.ourPokemon()},'soin')")
//                                .toD("sjms2:queue:" + jmsPrefix + "pokeInfirmerie?exchangePattern=InOut&requestTimeout=60000")
//                                .bean(pokemonGateway, "setLocalisationPokemon(${body.ourPokemon()},'mairie')")
//                            .otherwise()
//                .log("le body1 figthing session ${body}")
//                                .bean(pokemonGateway, "checkLastPokemon(${headers.idDresseur})")
//                                .choice()
//                                    .when(simple("${headers.isLastPokemon}"))
//                .log("le body figthing session ${body}")
//                                        .bean(pokemonGateway, "setLocalisationPokemon(${body},'store')" )
//                                        .marshal().json()
//                                        .split(body())
//                                            .log("body apres le split ${body}")
//                                   //     .to("sjms2:queue:M1.returnPNJ")
//                                       // .bean(pokemonGateway, "setLocalisationPokemon(${body.ourPokemon()},'store')")
//
//                                        .bean(dresseurGateway,"bannedDresseur(${headers.idDresseur})")
//                                        .to("sjms2:topic:M1.dresseurBanned")
//                                        .end()
//
//                                        .log("pas son dernier pokemon")
//                                .endChoice()
//                            .endChoice()
//                        .end() // Close the inner choice block
//
//                    .end() // Close the outer choice block
//
//                .end() // Close the entire choice block
//
//                .end()
//                .log("go back to store")
//               // .setBody(simple("${body.oponnent()}"))
//                .unmarshal().json(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class)
//                .bean(pokemonGateway, "setLocalisationPokemon(${body},'store')" )
//                .to("sjms2:queue:M1.returnPNJ");

        ;

    }

}


