package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;

public interface SoinService {

    fr.pantheonsorbonne.ufr27.miage.dto.Pokemon soignerPokemon(Pokemon pokemon);

    void redirectToMairie(Pokemon pokemon);

     int getPriceTreatment(Pokemon pokemon);
     void priseEnCharge(Pokemon pokemon);

}
