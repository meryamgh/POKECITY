package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.TreatmentSession;
import java.sql.Time;
import java.util.Collection;

public interface TreatmentDAO {

    void insertTreatmentSession(Pokemon pokemon, int treatPrice, int idDresseur);

    Collection<TreatmentSession> getAllTreatmentSessions();

    String getTimeTreatmentAsString(Time timeTreatment);

    Collection<TreatmentSession> getAllTreatmentSessionsByDresseuer(int idDresseur);
}
