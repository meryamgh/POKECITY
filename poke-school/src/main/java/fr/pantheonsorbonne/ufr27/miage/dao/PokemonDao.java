package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

public interface PokemonDao {

    Pokemon getPokemonById(int idPokemon);
    Integer getPokemonScoreById(int idPokemon);
    void setPokescorebyId(int id, int newPokescore);
}
