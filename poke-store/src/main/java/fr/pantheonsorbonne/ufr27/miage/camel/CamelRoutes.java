package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.TicketDresseurAchat;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    PurchasePokemonGateway pokemonGateway;

    @Override
    public void configure() throws Exception {



        from("sjms2:queue:" + jmsPrefix +"buyPokemonRoute")
                .choice()
                .when(body().isEqualTo(true))
                .bean(pokemonGateway,"getPokemon(${headers.idDresseur})")
                .to("sjms2:queue:" + jmsPrefix +"buyPokemonRoute")
                .otherwise()
                .bean(pokemonGateway, "notEnoughTogetPokemon")
                .log("le body : ${body}")
        ;
    }
}
