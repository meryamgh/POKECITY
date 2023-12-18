package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.Dresseur;

public interface SoinService {

    public boolean enoughMoney(Pokemon pokemon);

    public void soignerPokemon();

    public void redirectToMairie();
}
