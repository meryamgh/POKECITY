package fr.pantheonsorbonne.ufr27.miage.camel;



import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {


    @Inject
    CamelContext camelContext;

    @Inject
    FightSessionGateway fightSessionGateway;

    @Override
    public void configure() {


        from("sjms2:queue:M1.fight")
                .log("fight have begin ${body}")
                .bean(fightSessionGateway, "playBattle(${body},${headers.idDresseur})")
                .log("apres le combat ${body}")
                .delay(30000)
        ;

        from("sjms2:topic:M1.dresseurBanned")
                .log("${body}");

        from("sjms2:topic:M1.pokemonAddInOurCity")
                .log("${body}");

    }

}


