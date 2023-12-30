package fr.pantheonsorbonne.ufr27.miage.camel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    PurchasePokemonGateway pokemonGateway;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        this.camelContext.setTracing(true);

        from("direct:bankRoute")
                .marshal().json()
                .to("sjms2:queue:"+ jmsPrefix + "pokemonBuyCheckPrice?exchangePattern=InOut")
                .unmarshal().json(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class)
                .choice()
                .when(simple("${headers.responseHaveEnoughMoney}"))
                .bean(pokemonGateway,"getPokemon(${body}, ${headers.idDresseur})")
                .marshal().json()
                .to("sjms2:queue:"+ jmsPrefix + "pokemonBuy")
                .otherwise()
                .bean(pokemonGateway,"notEnoughTogetPokemon")
                .marshal().json();




        ;

        from("sjms2:queue:"+jmsPrefix+"pokeStore?exchangePattern=InOut")
                .bean(pokemonGateway,"getPokemon(${body}, ${headers.idDresseur})")
                .choice()
                .when(simple("${headers.responseHaveEnoughMoney}"))
                .to("sjms2:queue:"+jmsPrefix+"buyPokemon")
                .otherwise()
                .bean(pokemonGateway,"notEnoughTogetPokemon");

    }
}
