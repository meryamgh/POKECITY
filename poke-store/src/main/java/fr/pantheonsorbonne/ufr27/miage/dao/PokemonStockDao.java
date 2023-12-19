package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

import java.util.Collection;

public interface PokemonStockDao {

    Collection<Pokemon> getStock();

    Collection<Pokemon> getStockPokemonByPrice(int price);

    Pokemon getPokemonById(int idPokemon);

    void deletePokemon(int idPokemon);
}
