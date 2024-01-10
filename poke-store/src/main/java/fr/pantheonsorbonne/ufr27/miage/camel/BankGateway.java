package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import java.io.IOException;

@ApplicationScoped
public class BankGateway {

    @Inject
    CamelContext camelContext;

    public void checkBankCardDresseur(Pokemon pokemonToBuy) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeader("direct:bankRoute",new fr.pantheonsorbonne.ufr27.miage.dto.Pokemon(pokemonToBuy.getIdPokemon(),pokemonToBuy.getPrix(),pokemonToBuy.getPrix(),pokemonToBuy.getType(),false,pokemonToBuy.getName()),"price",pokemonToBuy.getPrix());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
