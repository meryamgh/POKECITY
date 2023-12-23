package fr.pantheonsorbonne.ufr27.miage.camel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {


    @Inject
    SchoolGateway gateway;

    @Override
    public void configure() throws Exception {

        from("direct:sendToMairie")
                .routeId("sendToMairieRoute")
                .log("Sending improved Pokemon to Mairie: ${body}")
                .marshal().json()
                .to("sjms2:queue:M1.Mairie");

        from("direct:sendSchoolSessionToMairie")
                .routeId("sendSessionToMairieRoute")
                .log("Sending right school session to Mairie: ${body}")
                .marshal().json()
                .to("sjms2:queue:M1.SessionToMairie");


        from("sjms2:queue:M1.PokemonToSchool")
                .log("Le pokemon est arrivé à l'école : ${body}")
                .unmarshal().json(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class)
                .bean(gateway, "collectingPokemon(${body})")
        ;

    }
}