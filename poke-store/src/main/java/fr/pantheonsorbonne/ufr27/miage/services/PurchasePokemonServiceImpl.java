package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.PurchasePokemonGateway;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.ReceiptPokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PurchasePokemonServiceImpl implements PurchasePokemonService{

    @Inject
    PurchasePokemonGateway pokemonGateway;


    @Override
    public void buyPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemonToBuy) {
        pokemonGateway.buyPokemon(pokemonToBuy.pokeScore());
    }
}
