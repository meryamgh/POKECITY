package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.exception.NotAvailablePokemonException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

public interface SchoolService {
    void sendPokemonToSchool(int pokemon) throws NotAvailablePokemonException;

    Pokemon getPokemon(int id);
}
