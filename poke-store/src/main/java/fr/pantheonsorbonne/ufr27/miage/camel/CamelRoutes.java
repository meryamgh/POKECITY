package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
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

    @Inject
    FightGateway fightGateway;

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

        from("sjms2:queue:M1.getPokemonForFight?exchangePattern=InOut")
                .unmarshal().json(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class)
                .bean(fightGateway,"getRandomPokemonForFight(${body})")
                ;

//        from("sjms2:queue:M1.returnPNJ")
//                .unmarshal().json(Pokemon.class)
//                .bean(fightGateway, "stockPokemonToStore(${body})");
//

        from("sjms2:queue:M1.returnPNJ")
                .choice()
                .when().simple("${body} != null && ${body.getClass().getName()} == 'fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class'")
                .bean(fightGateway, "stockPokemonToStore(${body})")
                .otherwise()
                .log("Ignoring non-Pokemon message");



        from("sjms2:topic:M1.dresseurBanned")
                .log("DRESSEUR WITH ID ${headers.idDresseur} IS BANNED");
    }
}
