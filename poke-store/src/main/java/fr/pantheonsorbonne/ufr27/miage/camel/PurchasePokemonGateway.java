package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.services.StoreService;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import jakarta.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class PurchasePokemonGateway {

    @Inject
    CamelContext camelContext;

    @Inject
    StoreService storeService;


    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    public void checkBankCardDresseur(int pokemonToBuy,int price) {
        System.out.println("le id poke"+pokemonToBuy+" le price "+price);
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody("sjms2:queue:" + jmsPrefix +"bankRoute",new Pokemon(pokemonToBuy,price));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getPokemon(int idPokemon,int idDresseur) throws PokemonNotFoundException {
        this.storeService.deletePokemon(idPokemon);


    }

    public void notEnoughTogetPokemon(){
        System.out.println("PAS ASSEZ D4ARGNET");
    }
}