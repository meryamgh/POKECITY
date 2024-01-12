package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

import java.util.Collection;

public interface InventoryPokemonService {
    Collection<Pokemon> getAllPokemon();
    Collection<Pokemon> getPokemonByPrice(int price);
    void deletePokemon(int idPokemon)throws PokemonNotFoundException ;

    Pokemon getRandomPokemonFighting() throws PokemonNotFoundException;

    void addPokemonToStore(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon);

    fr.pantheonsorbonne.ufr27.miage.dto.Pokemon addNewPokemonToStore();

}
