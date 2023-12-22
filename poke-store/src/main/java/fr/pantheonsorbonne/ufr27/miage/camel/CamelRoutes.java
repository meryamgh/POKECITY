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

        from("sjms2:queue:" + jmsPrefix +"pokemonSalled")
                .bean(pokemonGateway,"getPokemon(${body}, ${headers.idDresseur})");

        from("sjms2:queue:"+jmsPrefix+"pokeStore")
                .bean(pokemonGateway,"getPokemon(${body}, ${headers.idDresseur})");


    }
}
