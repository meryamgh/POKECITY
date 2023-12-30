package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.exception.NotAvailablePokemonException;

public interface FightService {

    void fight(int idPokemon)  throws NotAvailablePokemonException;

}
