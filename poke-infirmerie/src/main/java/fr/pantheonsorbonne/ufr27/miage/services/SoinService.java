package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.TreatmentSession;

import java.util.Collection;

public interface SoinService {

    fr.pantheonsorbonne.ufr27.miage.dto.Pokemon soignerPokemon(Pokemon pokemon, int idDresseur);

     int getPriceTreatment(Pokemon pokemon);
    Collection<TreatmentSession> getAllTreatmentSessionsDresseur(int idDresseur);

    Collection<TreatmentSession> getAllTreatmentSessions();

}
