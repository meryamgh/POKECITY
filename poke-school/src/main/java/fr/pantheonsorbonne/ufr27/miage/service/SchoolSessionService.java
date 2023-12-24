package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.SchoolSession;

import java.util.Collection;

public interface SchoolSessionService {
    Collection<SchoolSession> getAllSessions();
    SchoolSession findRightSession(int pokescore);
}
