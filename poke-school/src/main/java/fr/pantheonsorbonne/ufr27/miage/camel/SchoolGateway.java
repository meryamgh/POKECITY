package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class SchoolGateway {

    @Inject
    CamelContext context;

    public void sendImprovedPokemonToMairie(Pokemon pokemon) {
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            producer.sendBody("direct:sendToMairie",pokemon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



