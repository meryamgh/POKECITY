package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.services.DresseurService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {


    @Inject
    BankGateway bank;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.dresseurId")
    int idDresseur;

    @Override
    public void configure() throws Exception {
        from("sjms2:queue:" + jmsPrefix +"bankRoute")
                .log("Le body est arriver a la mairie pour le check bank ${body}")
                .setHeader("idDresseur", constant(idDresseur))
                .bean(bank, "checkBalance(${headers.price}, ${headers.idDresseur})")
                .log("Le body MAIRIE BANK CHECKED ${body} le header ${headers.idDresseur}")
                .log("avant le systeme: ${headers.source},money: ${headers.responseHaveEnoughMoney}")

                .choice()
                .when(simple("${headers.source} == 'pokeStore'"))
                .bean(bank, "checkBalance2(${body},  ${headers.idDresseur})")
                .otherwise()
                .log("pas pokstore")
                .end()
                .log("le systeme: ${headers.source},money: ${headers.responseHaveEnoughMoney}")
                .toD("sjms2:queue:"+jmsPrefix+"${headers.source}")

        ;

        from("direct:checkBankAccount")
                .setHeader("idDresseur", constant(idDresseur))
                .bean(bank, "checkBalance(${body}, ${headers.idDresseur})")
                .log("Le body MAIRIE BANK CHECKED ${body} le header ${headers.idDresseur}")
                .to("sjms2:queue:" + jmsPrefix +"buyPokemonRoute")
        ;

        from("sjms2:queue:M1.Mairie").log("${body}");

    }

}


