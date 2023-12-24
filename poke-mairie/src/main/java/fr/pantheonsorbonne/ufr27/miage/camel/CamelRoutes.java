package fr.pantheonsorbonne.ufr27.miage.camel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {


    @Inject
    BankGateway bank;

    @Inject
    PokemonGateway pokemon;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.dresseurId")
    Integer idDresseur;

    @Override
    public void configure() throws Exception {
        from("sjms2:queue:" + jmsPrefix +"bankRoute")
                .log("Le body est arriver a la mairie pour le check bank ${body}")
                .setHeader("idDresseur", constant(idDresseur))
                .bean(bank, "checkBalance(${body}, ${headers.idDresseur})")
                .log("Le body MAIRIE BANK CHECKED ${body} le header ${headers.idDresseur}")
               // .to("sjms2:queue:" + jmsPrefix +"buyPokemonRoute")
        ;

        from("direct:checkBankAccount")
                .log("Le body est arriver a la mairie pour le check bank ${body}")
                .setHeader("idDresseur", constant(idDresseur))
                .bean(bank, "checkBalance(${body}, ${headers.idDresseur})")
                .log("Le body MAIRIE BANK CHECKED ${body} le header ${headers.idDresseur}")
                .to("sjms2:queue:" + jmsPrefix +"buyPokemonRoute")
        ;

        from("direct:sendPokemonToSchool")
                .routeId("sendPokemonToSchoolRoute")
                .log("Sending pokemon to school: ${body}")
                .marshal().json()
                .to("sjms2:queue:M1.PokemonToSchool?exchangePattern=InOut")
                .log("body : ${body}, header : ${header.price}")
                .setHeader("idDresseur", constant(idDresseur))
                .bean(bank, "checkBalance(${headers.price}, ${headers.idDresseur})")
                .choice()
                .when(simple("${headers.responseHaveEnoughMoney}"))
                .to("sjms2:queue:M1.BankResponse?exchangePattern=InOut")
                .bean(pokemon, "improvePokemon(${body})")
                .log("improved pokemon : ${body}")
                .otherwise()
                .log("pas assez d'argent")

        ;


    }
}
