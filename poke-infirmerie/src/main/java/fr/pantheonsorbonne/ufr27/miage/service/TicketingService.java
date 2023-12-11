package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchTicketException;
import fr.pantheonsorbonne.ufr27.miage.dto.ETicket;
import fr.pantheonsorbonne.ufr27.miage.exception.CustomerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ExpiredTransitionalTicketException;

public interface TicketingService {
    String emitTicket(ETicket eticket) throws ExpiredTransitionalTicketException, NoSuchTicketException, CustomerNotFoundException.NoSeatAvailableException;

    void cleanUpTransitionalTicket(int transitionalTicketId);

    boolean validateTicket(Ticket t);
}
