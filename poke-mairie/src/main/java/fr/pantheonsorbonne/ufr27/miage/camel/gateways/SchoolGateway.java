package fr.pantheonsorbonne.ufr27.miage.camel.gateways;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class SchoolGateway {

    @Inject
    CamelContext context;


    public void sendToSchool(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon){
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            producer.sendBody("direct:sendPokemonToSchool",pokemon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
