package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

import java.util.Collection;

public interface StoreService {

    Collection<Pokemon> getAllPokemon();

    void buyPokemon(int idPokemon);
    Collection<Pokemon> getPokemonByPrice(int price);
}
