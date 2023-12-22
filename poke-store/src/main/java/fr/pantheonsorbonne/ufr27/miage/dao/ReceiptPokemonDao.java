package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.ReceiptPokemon;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;


public interface ReceiptPokemonDao {

    ReceiptPokemon insertReceiptTicket(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon idPokemon, int idDresseur);

    Collection<ReceiptPokemon> getAllReceiptsOfStore();

}
