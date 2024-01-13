package fr.pantheonsorbonne.ufr27.miage.camel.gateways;

import fr.pantheonsorbonne.ufr27.miage.camel.PokemonGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    PokemonGateway pokemonGateway;

    @Inject
    FightGateway fightGateway;

    @Override
    public void configure(){


        from("direct:bankRoute")
                .marshal().json()
                .to("sjms2:queue:"+ jmsPrefix + "pokemonBuyCheckPrice?exchangePattern=InOut")
                .choice()
                .when(simple("${headers.success}"))
                .unmarshal().json(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class)
                .bean(pokemonGateway,"getPokemon(${body}, ${headers.idDresseur})")
                .marshal().json()
                .to("sjms2:queue:"+ jmsPrefix + "pokemonBuy")
                .otherwise()
                .bean(pokemonGateway,"enableToGetPokemon")
                .marshal().json();


        from("sjms2:queue:"+ jmsPrefix + ".getPokemonForFight?exchangePattern=InOut")
                .unmarshal().json(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class)
                .log("${body}")
                .bean(fightGateway,"getRandomPokemonForFight(${body})")
                ;

        from("sjms2:queue:"+ jmsPrefix + ".returnPNJ")
                .log("recu dans le store pour etre ajouter dans ma bdd ${body}")
                .bean(fightGateway, "stockPokemonToStore(${body})");

        from("sjms2:topic:"+ jmsPrefix + ".dresseurBanned")
                .log("${body}");

        from("scheduler://pokemonProduction?delay=70000")
                .bean(pokemonGateway, "createProduct()")
                .log("Product created : ${body}")
                .to("sjms2:queue:"+ jmsPrefix +".newPokemon");

        from("sjms2:topic:"+ jmsPrefix + ".pokemonAddInOurCity")
                .log("${body}");
    }
}
