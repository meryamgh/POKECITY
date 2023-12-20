package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

public interface PokemonDao {

    Pokemon getPokemonById(int idPokemon);

    void changeStatus(Pokemon idPokemon, int idDresseur, boolean status);
}