package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.ReceiptPokemon;

public interface PurchasePokemonService {

    void buyPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon);
}
