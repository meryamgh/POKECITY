package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.services.SoinService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
@ApplicationScoped
public class CamelRoutes extends RouteBuilder {
    @Inject
    SoinService soinService;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;


    @Override
    public void configure() throws Exception {

        from("direct:checkBankAccount")
                .log("On check le compte en banque du dresseur pour le traitement ${body}")
                .to("sjms2:M1.bank")
        ;

        from("direct:redirectToMairie")
                .log("on redirige vers la mairie le pokemon qui ne peut pas etre soigné : ${body}")
                .to("sjms2:M1.mairie");


        from("sjms2:queue:" + jmsPrefix + "pokemonSaled")
                .bean(soinService, "soignerPokemon(${body.pokemonToCure})");
    }
}
