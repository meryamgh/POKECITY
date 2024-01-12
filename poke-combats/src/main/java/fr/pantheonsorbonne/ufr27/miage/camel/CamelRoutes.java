package fr.pantheonsorbonne.ufr27.miage.camel;



import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    FightSessionGateway fightSessionGateway;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Override
    public void configure() {



        from("sjms2:queue:"+ jmsPrefix +".fight")
                .log("fight have begin ${body}")
                .bean(fightSessionGateway, "playBattle(${body},${headers.idDresseur})")
                .log("apres le combat ${body}")
                .delay(30000)
        ;

        from("sjms2:topic:"+ jmsPrefix +".dresseurBanned")
                .log("${body}");

        from("sjms2:topic:"+ jmsPrefix +".pokemonAddInOurCity")
                .log("${body}");

    }

}


