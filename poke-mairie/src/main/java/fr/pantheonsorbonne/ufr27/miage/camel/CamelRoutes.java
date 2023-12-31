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
    CamelContext camelContext;



    @Override
    public void configure() throws Exception {
        this.camelContext.setTracing(true);
        from("sjms2:queue:" + jmsPrefix +"bankRoute")
                .log("Le body est arriver a la mairie pour le check bank ${body}")
                .setHeader("idDresseur", constant(idDresseur))
                .bean(bank, "checkBalance(${headers.price}, ${headers.idDresseur})")
                .log("le systeme: ${headers.source},money: ${headers.responseHaveEnoughMoney}")

                .toD("sjms2:queue:" + jmsPrefix + "${headers.source}?exchangePattern=InOut&requestTimeout=60000")

                .log("apres le toD le body : ${body}")
                .choice()
                .when(simple("${headers.source}").isEqualTo("pokestore"))
                .bean(pokemonGateway, "affectPokemonToDresseur(${body},  ${headers.idDresseur})")
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
                .to("sjms2:queue:M1.fight")
        ;

    }

}


