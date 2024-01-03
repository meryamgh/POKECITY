package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

import java.util.Collection;

public interface DresseurService {

    void affectPokemonToDresseur(int idPokemon, int idDresseur);

    Collection<Pokemon> getAllDresseurPokemon(int idDresseur);

    boolean checkIfLastPokemon(int idDresseur);

    void changeBannedStatus(int idDresseur);

}
