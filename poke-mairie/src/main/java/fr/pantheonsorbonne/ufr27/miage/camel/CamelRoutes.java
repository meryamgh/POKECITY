package fr.pantheonsorbonne.ufr27.miage.camel;

import org.apache.camel.builder.RouteBuilder;

public class CamelRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("sjms2:M1.bank")
                .log("Le body est arriver a la mairie pour le check bank ${body}");
    }
}
