package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.services.InventoryPokemonService;
import fr.pantheonsorbonne.ufr27.miage.services.ReceiptService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.logging.Logger;


@ApplicationScoped
public class PokemonGateway {

    @Inject
    ReceiptService storeService;

    @Inject
    InventoryPokemonService pokemonService;

    private static final Logger LOGGER = Logger.getLogger("logger");


    public void getPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemonSalled,int idDresseur) throws PokemonNotFoundException {
        this.storeService.pokemonSalled(pokemonSalled, idDresseur);
    }

    public void enableToGetPokemon() {
        LOGGER.info("Can not get pokemon");
    }

    public fr.pantheonsorbonne.ufr27.miage.dto.Pokemon createProduct(){
        return this.pokemonService.addNewPokemonToStore();
    }

}