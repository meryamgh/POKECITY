package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.TreatmentSession;

import java.sql.Time;
import java.util.Collection;

public interface TreatmentDAO {

    public TreatmentSession insertTreatmentSession(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon);

    public Collection<TreatmentSession> getAllTreatmentSessions();

    public String getTimeTreatmentAsString(Time timeTreatment);
}
