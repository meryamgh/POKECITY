package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

import java.util.Collection;

public interface PokemonDao {

    Pokemon getPokemonById(int idPokemon)  throws PokemonNotFoundException;

    void changeStatus(Pokemon idPokemon, int idDresseur, boolean status);

    void setPokescorebyId(int idPokemon, int newPokescore);

    void setLocalisation(int idPokemon, String loca);

    Collection<Pokemon> getAllPokemon();

    Collection<Pokemon> getPokemonByLocation(String localisation);

    void addNewPokemon(Pokemon pokemon);

}
