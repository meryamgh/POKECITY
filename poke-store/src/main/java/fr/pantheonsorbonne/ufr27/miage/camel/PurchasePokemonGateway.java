package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.services.ReceiptService;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import jakarta.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class PurchasePokemonGateway {

    @Inject
    CamelContext camelContext;

    @Inject
    ReceiptService storeService;


    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    public void checkBankCardDresseur(int pokemonToBuy,int price) {
        System.out.println("le id poke"+pokemonToBuy+" le price "+price);
        Map<String, Object> headers = new HashMap<>();
        headers.put("source", "pokeStore");
        headers.put("price", price);
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeaders("direct:bankRoute",new fr.pantheonsorbonne.ufr27.miage.dto.Pokemon(pokemonToBuy,price),headers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemonSalled,int idDresseur) throws PokemonNotFoundException {
        this.storeService.pokemonSalled(pokemonSalled, idDresseur);
    }

    public void notEnoughTogetPokemon(){
        System.out.println("PAS ASSEZ D4ARGNET");
    }
}