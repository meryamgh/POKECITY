package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.ReceiptPokemon;

import java.util.Collection;


public interface ReceiptPokemonDao {

    void insertReceiptTicket(Pokemon idPokemon, int idDresseur);

    Collection<ReceiptPokemon> getAllReceiptsOfStore();

    Collection<ReceiptPokemon> getAllReceiptsOfStoreByDresseur(int iDresseur);

}
