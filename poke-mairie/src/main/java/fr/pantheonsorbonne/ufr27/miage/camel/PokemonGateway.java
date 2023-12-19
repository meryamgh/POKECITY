package fr.pantheonsorbonne.ufr27.miage.camel;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@ApplicationScoped
public class PokemonGateway {

    @Inject
    CamelContext camelContext;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    public void pokemonSalled(int pokemonSalled,int idDresseur) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeader("sjms2:queue:" + jmsPrefix +"pokemonSalled",pokemonSalled
                    ,"idDresseur",idDresseur);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
