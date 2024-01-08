package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ReceiptPokemon;

import java.util.Collection;

public interface ReceiptService {
    void buyPokemon(int idPokemon) throws PokemonNotFoundException ;


    Collection<ReceiptPokemon> getAllReceiptsByDresseur(int idDresseur);

    void createReceiptPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon idPokemon, int idDresseur);

    void pokemonSalled(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon idPokemon, int idDresseur) throws PokemonNotFoundException;

    Collection<ReceiptPokemon> getAllReceipts();
}
