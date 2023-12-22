package fr.pantheonsorbonne.ufr27.miage.camel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class RedirectToMairieGateway {
    @Inject
    CamelContext camelContext;

    public void redirectToMairie(Pokemon pokemon) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody("direct:redirectToMairie", pokemon);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
