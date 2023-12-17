package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolSession;

import java.util.Collection;

public interface SchoolSessionDao {

    Collection<SchoolSession> getSchoolSessions();
}
