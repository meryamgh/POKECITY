package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.SchoolTicket;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Collection;

@ApplicationScoped
public class SchoolTicketDaoImpl implements SchoolTicketDao{

    @Inject
    EntityManager em;

    @Override
    public SchoolTicket getSchoolTicketById(int idSchoolTicket){
        return em.find(SchoolTicket.class, idSchoolTicket);
    }

    @Override
    @Transactional
    public SchoolTicket createSchoolTicket(int idPokemon, int idSession)
    {
        SchoolTicket s = new SchoolTicket();
        s.setPokemon_id(idPokemon);
        s.setSchoolSession_id(idSession);
        em.persist(s);
        return s;
    }

    @Override
    public Collection<SchoolTicket> getSchoolTickets() {
        return em.createQuery("SELECT ticket FROM SchoolTicket ticket", SchoolTicket.class).getResultList();
    }
}

