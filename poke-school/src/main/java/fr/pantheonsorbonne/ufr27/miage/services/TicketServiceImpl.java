package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dao.SchoolTicketDao;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolTicket;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Collection;

@ApplicationScoped
public class TicketServiceImpl implements TicketService{

    @Inject
    SchoolTicketDao schoolTicketDao;

    @Override
    public Collection<SchoolTicket> getAllTickets() {
       return schoolTicketDao.getSchoolTickets();
    }

    @Override
    public Collection<SchoolTicket> getAllTicketsByDresseur(int idDresseur) {
        return schoolTicketDao.getSchoolTicketsByDresseur(idDresseur);
    }



}
