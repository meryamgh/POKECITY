package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.services.SoinService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class RedirectToMairieGateway {
    @Inject
    CamelContext camelContext;

    @Inject
    SoinService service;

    public void redirectToMairie(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody("direct:redirectToMairie", pokemon);
            System.out.println("redirectToMairie" + pokemon.prix());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void redirect(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon) {
        service.redirectToMairie(pokemon);
    }


}
