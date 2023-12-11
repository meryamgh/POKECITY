package fr.pantheonsorbonne.ufr27.miage.dao;

public interface TicketDAO {
    Ticket findTicket(int transitionalTicketId) throws NoSuchTicketException;

    Ticket emitTicketForCustomer(int transitionalTickerId, Customer customer);

    void removeTransitionalTicket(int transitionalTicketId);
}
