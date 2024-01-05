package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.services.ReceiptService;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import jakarta.inject.Inject;
import java.io.IOException;


@ApplicationScoped
public class PurchasePokemonGateway {

    @Inject
    CamelContext camelContext;

    @Inject
    ReceiptService storeService;


    public void checkBankCardDresseur(int pokemonToBuy,int price, String type, boolean isAdopted, String name) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeader("direct:bankRoute",new fr.pantheonsorbonne.ufr27.miage.dto.Pokemon(pokemonToBuy,price,price, type, isAdopted, name),"price",price);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemonSalled,int idDresseur) throws PokemonNotFoundException {
        this.storeService.pokemonSalled(pokemonSalled, idDresseur);
    }

    public void notEnoughTogetPokemon() {
        System.out.println("PAS ASSEZ D4ARGNET");
    }
}