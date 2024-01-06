package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

import java.util.Collection;

public interface PokemonService {


    void updatePokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon);

    void updatePokemonLocalisation(int idPokemon,String newLocalisation);

    Collection<Pokemon> getAllPokemon();

}
