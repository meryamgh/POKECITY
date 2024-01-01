package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.SchoolSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Collection;

@ApplicationScoped
public class SchoolSessionDaoImpl implements SchoolSessionDao{

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public Collection<SchoolSession> getSchoolSessions() {
        return this.em.createQuery("SELECT session FROM SchoolSession session", SchoolSession.class).getResultList();
    }

    @Override
    @Transactional
    public SchoolSession getSchoolSessionById(int id) {
        return this.em.find(SchoolSession.class, id);
    }

}