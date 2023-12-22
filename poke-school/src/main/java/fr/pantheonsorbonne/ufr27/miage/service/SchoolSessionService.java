package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolSession;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolTicket;

import java.util.Collection;

public interface SchoolSessionService {
    Collection<SchoolSession> getAllSessions();
    Collection<SchoolTicket> getAllTickets();
    void inscrirePokemon(Pokemon pokemon, SchoolSession session);
    boolean isMoneyEnough();
    SchoolSession findRightSession(Pokemon pokemon);
    void sendSessionToMairie(SchoolSession session);
    void getPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon);
}