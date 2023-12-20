package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.TreatmentSession;
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
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    public void checkBankAccount(TreatmentSession treatmentSession) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody("direct:checkBankAccount", treatmentSession);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
