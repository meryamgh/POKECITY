package fr.pantheonsorbonne.ufr27.miage.camel;

import org.apache.camel.builder.RouteBuilder;

public class CamelRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("direct:sendToMairie")
                .routeId("sendToMairieRoute")
                .log("Sending improved Pokemon to Mairie: ${body}")
                .marshal().json()
                .to("sjms2:queue:M1.Mairie");
    }
}