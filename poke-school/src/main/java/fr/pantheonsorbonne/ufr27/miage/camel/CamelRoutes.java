package fr.pantheonsorbonne.ufr27.miage.camel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {


    @Inject
    SchoolGateway schoolGateway;

    @Override
    public void configure() throws Exception {


        from("sjms2:queue:M1.PokemonToSchool?exchangePattern=InOut")
                .log("Le pokemon est arrivé à l'école : ${body}")
                .unmarshal().json(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class)
                .bean(this.schoolGateway, "getPriceRightSession(${body})")
                .marshal().json()

        ;

        from("sjms2:queue:M1.BankResponse?exchangePattern=InOut")
                .unmarshal().json(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon.class)
                .bean(this.schoolGateway, "improvePokemon(${body})")
        ;


    }
}