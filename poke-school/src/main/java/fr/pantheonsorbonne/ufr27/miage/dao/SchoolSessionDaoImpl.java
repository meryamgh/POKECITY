package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.Collection;

@ApplicationScoped
public class SchoolSessionDaoImpl implements SchoolSessionDao{

    @Inject
    EntityManager em;
    @Override
    public Collection<SchoolSession> getSchoolSessions() {
        return em.createQuery("SELECT session FROM SchoolSession session", SchoolSession.class).getResultList();
    }
}
