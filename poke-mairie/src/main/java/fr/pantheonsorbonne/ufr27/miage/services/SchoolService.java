package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.exception.DresseurBannedException;
import fr.pantheonsorbonne.ufr27.miage.exception.NotAvailablePokemonException;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

public interface SchoolService {
    void sendPokemonToSchool(int pokemon) throws NotAvailablePokemonException, PokemonNotFoundException, DresseurBannedException;

    Pokemon getPokemon(int id) throws PokemonNotFoundException;
}
