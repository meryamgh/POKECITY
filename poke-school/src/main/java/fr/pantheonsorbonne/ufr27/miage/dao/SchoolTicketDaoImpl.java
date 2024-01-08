package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.SchoolTicket;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.Date;

@ApplicationScoped
public class SchoolTicketDaoImpl implements SchoolTicketDao{

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public SchoolTicket getSchoolTicketById(int idSchoolTicket){
        return em.find(SchoolTicket.class, idSchoolTicket);
    }

    @Override
    @Transactional
    public SchoolTicket createSchoolTicket(int idPokemon, int idSession, int cost, int idDresseur)
    {
        SchoolTicket s = new SchoolTicket();
        s.setPokemon_id(idPokemon);
        s.setCost(cost);
        s.setIdDresseur(idDresseur);
        s.setSchoolSession_id(idSession);
        s.setSchoolSessionDate(new Date());
        em.persist(s);
        return s;
    }

    @Override
    @Transactional
    public Collection<SchoolTicket> getSchoolTickets() {
        return em.createQuery("SELECT ticket FROM SchoolTicket ticket", SchoolTicket.class).getResultList();
    }

    @Override
    @Transactional
    public Collection<SchoolTicket> getSchoolTicketsByDresseur(int idDresseur){
        return em.createQuery("SELECT ticket FROM SchoolTicket ticket WHERE ticket.idDresseur = :idDresseur", SchoolTicket.class)
                .setParameter("idDresseur", idDresseur)
                .getResultList();

    }
}