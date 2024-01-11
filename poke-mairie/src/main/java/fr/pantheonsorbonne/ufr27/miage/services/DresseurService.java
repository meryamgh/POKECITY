package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.exception.DresseurBannedException;
import fr.pantheonsorbonne.ufr27.miage.exception.NotAvailablePokemonException;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

import java.util.Collection;

public interface DresseurService {

    void affectPokemonToDresseur(int idPokemon, int idDresseur) throws PokemonNotFoundException;

    Collection<Pokemon> getAllDresseurPokemon(int idDresseur);

    boolean isDresseurOutOfPokemons(int idDresseur);

    void changeBannedStatus(int idDresseur);

    void deletePokemonDresseur(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon, int idDresseur) throws PokemonNotFoundException;

    void checkDresseurBanned(int idDresseur) throws DresseurBannedException;

    void checkDresseurCanPlayWithPokemon(int idDresseur, Pokemon pokemon) throws NotAvailablePokemonException;

    void checkAvailaibilityToPlay(int idDresseur, Pokemon pokemon) throws DresseurBannedException, NotAvailablePokemonException;

}
