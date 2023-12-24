package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

public interface SchoolService {
    void sendPokemonToSchool(Pokemon pokemon);

    Pokemon getPokemon(int id);
}
