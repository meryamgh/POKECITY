package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.SchoolTicket;

import java.util.Collection;

public interface SchoolTicketDao {
    public SchoolTicket getSchoolTicketById(int idSchoolTicket);
    public SchoolTicket createSchoolTicket(int idPokemon, int idSession, int cost, int idDresseur);
    public Collection<SchoolTicket> getSchoolTickets();
    Collection<SchoolTicket> getSchoolTicketsByDresseur(int idDresseur);
}