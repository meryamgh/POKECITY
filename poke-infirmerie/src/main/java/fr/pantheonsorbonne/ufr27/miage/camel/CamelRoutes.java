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
                .when(simple("${headers.success}"))
                .delay(30000)
                .bean(soignerPokemonGateway, "soigner(${body}, ${headers.idDresseur})");

        from("sjms2:queue:M1.soin")
                .log("pokemon recu à l'infirmerie ${body}")
                .setBody(simple("${body}"))
                .log("pokemon recu à l'infirmerie ${body}")
                .bean(soignerPokemonGateway, "getPriceTreatment")
        ;

        from("sjms2:topic:M1.dresseurBanned")
                .log("DRESSEUR WITH ID ${headers.idDresseur} IS BANNED");

    }
}
