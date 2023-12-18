package fr.pantheonsorbonne.ufr27.miage.camel;

import org.apache.camel.builder.RouteBuilder;

public class CamelRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("direct:checkBankAccount")
                .log("le body : ${body}")
                .to("sjms2:M1.bank")
        ;

    }
}
