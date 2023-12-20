package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

import java.util.Collection;

public interface StoreService {

    Collection<Pokemon> getAllPokemon();

    void buyPokemon(int idPokemon) throws PokemonNotFoundException;
    Collection<Pokemon> getPokemonByPrice(int price);

    void deletePokemon(int idPokemon) throws PokemonNotFoundException;

    void createReceiptPokemon(int idPokemon, int idDresseur);
}
