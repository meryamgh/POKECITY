package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.exception.DresseurBannedException;
import fr.pantheonsorbonne.ufr27.miage.exception.NotAvailablePokemonException;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;

public interface FightService {

    void fight(int idPokemon) throws NotAvailablePokemonException, DresseurBannedException, PokemonNotFoundException;

}
