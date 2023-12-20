package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.SchoolSession;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolTicket;

import java.util.Collection;

public interface SchoolSessionService {
    Collection<SchoolSession> getAllSessions();
    Collection<SchoolTicket> getAllTickets();
    void inscrirePokemon(int idPokemon, int idSession);
    boolean isMoneyEnough();
}
