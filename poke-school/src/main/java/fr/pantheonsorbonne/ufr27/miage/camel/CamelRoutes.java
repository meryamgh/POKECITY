package fr.pantheonsorbonne.ufr27.miage.camel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {


    @Inject
    SchoolGateway gateway;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Override
    public void configure() {


        from("sjms2:queue:"+ jmsPrefix + ".PokemonToSchool?exchangePattern=InOut")
                .log("Le pokemon est arrivé à l'école : ${body}")
                .unmarshal().json(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class)
                .bean(gateway, "getPriceRightSession(${body})")
                .marshal().json()
        ;

        from("sjms2:queue:"+ jmsPrefix +".BankResponse?exchangePattern=InOut")
                .unmarshal().json(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class)
                .delay(30000)
                .bean(gateway, "improvePokemon(${body}, ${headers.idDresseur})")
        ;


        from("sjms2:topic:"+ jmsPrefix + ".dresseurBanned")
                .log("${body}");

        from("sjms2:topic:"+ jmsPrefix + ".pokemonAddInOurCity")
                .log("${body}");

    }
}