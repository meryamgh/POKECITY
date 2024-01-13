package fr.pantheonsorbonne.ufr27.miage.camel.gateways;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class FightGateway {

    @Inject
    CamelContext context;


    public void retrievePokemonFromStoreToFight(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon){
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            producer.sendBody("direct:fightingPokemon",pokemon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}