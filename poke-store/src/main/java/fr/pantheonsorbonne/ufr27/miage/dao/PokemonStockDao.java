package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

import java.util.Collection;

public interface PokemonStockDao {

    Collection<Pokemon> getStock();

    Collection<Pokemon> getStockPokemonByPrice(int price);

    Pokemon getPokemonById(int idPokemon) throws PokemonNotFoundException  ;

    void deletePokemon(int idPokemon)throws PokemonNotFoundException  ;

    Pokemon getRandomPokemon() throws PokemonNotFoundException;

    void addPokemonToStore(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon);

}
