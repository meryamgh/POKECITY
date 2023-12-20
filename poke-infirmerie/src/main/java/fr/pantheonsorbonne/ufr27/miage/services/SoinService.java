package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.Dresseur;

public interface SoinService {

    public boolean enoughMoney(int idDresseur, int priceTreatment);

    public void soignerPokemon();

    public void redirectToMairie(int idPokemon);

    public int getPriceTreatment(int idPokemon);

}
