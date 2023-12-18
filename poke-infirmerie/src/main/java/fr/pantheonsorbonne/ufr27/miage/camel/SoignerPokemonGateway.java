package fr.pantheonsorbonne.ufr27.miage.camel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@ApplicationScoped
public class SoignerPokemonGateway {

    @Inject
    CamelContext camelContext;

    public void checkBankAccount(int pokeScoreDresseur) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody("direct:checkBankAccount", pokeScoreDresseur);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
