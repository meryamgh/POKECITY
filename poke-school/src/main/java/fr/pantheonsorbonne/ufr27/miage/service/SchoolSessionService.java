package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolSession;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolTicket;

import java.util.Collection;

public interface SchoolSessionService {
    Collection<SchoolSession> getAllSessions();
    Collection<SchoolTicket> getAllTickets();
    void inscrirePokemon(SchoolSession session);
    boolean isMoneyEnough();
    SchoolSession findRightSession();
    void sendSessionToMairie(SchoolSession session);
    void collectPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon);
    Pokemon getPokemon();
    void resetPokemon();
}