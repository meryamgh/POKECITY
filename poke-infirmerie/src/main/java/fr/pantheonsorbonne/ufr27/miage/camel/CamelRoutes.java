package fr.pantheonsorbonne.ufr27.miage.camel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    SoignerPokemonGateway soignerPokemonGateway;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;


    @Override
    public void configure() throws Exception {

        from("sjms2:queue:" + jmsPrefix + "pokeInfirmerie")
                .choice()
                .when(simple("${headers.responseHaveEnoughMoney}"))
                .delay(30000)
                .bean(soignerPokemonGateway, "soigner(${body})");


    }
}
