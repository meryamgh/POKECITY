package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.model.SchoolTicket;
import java.util.Collection;

public interface TicketService {
    Collection<SchoolTicket> getAllTickets();


    Collection<SchoolTicket> getAllTicketsByDresseur(int idDresseur);
}
