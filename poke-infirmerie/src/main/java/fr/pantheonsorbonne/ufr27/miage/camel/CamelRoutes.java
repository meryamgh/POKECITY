package fr.pantheonsorbonne.ufr27.miage.camel;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class CamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:checkBankAccount")
                .log("On check le compte en banque du dresseur pour le traitement ${body}")
                .to("sjms2:M1.bank")
        ;

        from("direct:redirectToMairie")
                .log("on redirige vers la mairie le pokemon qui ne peut pas etre soigné : ${body}")
                .to("sjms2:M1.mairie") ;
        }
}
