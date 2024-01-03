package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.services.SoinService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class SoignerPokemonGateway {

    @Inject
    CamelContext camelContext;
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    SoinService service;

    public void checkBankAccount(Pokemon pokemon,int treatPrice) {
        System.out.println("le id poke"+pokemon.name()+" le price "+pokemon.prix());
        Map<String, Object> headers = new HashMap<>();
        headers.put("source", "pokeInfirmerie");
        headers.put("price", treatPrice);
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeaders("sjms2:queue:" + jmsPrefix +"bankRoute",pokemon ,headers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public fr.pantheonsorbonne.ufr27.miage.dto.Pokemon soigner(Pokemon pokemon) {
        return service.soignerPokemon(pokemon);
    }

    public void getPriceTreatment(Pokemon pokemon, Exchange exchange){
        System.out.println(this.service.getPriceTreatment(pokemon));
        exchange.getIn().setHeader("price",this.service.getPriceTreatment(pokemon));

    }

}
