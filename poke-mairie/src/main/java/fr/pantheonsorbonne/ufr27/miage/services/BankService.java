package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dto.TicketDresseurAchat;

public interface BankService {

    TicketDresseurAchat checkBalance(TicketDresseurAchat ticket);
}
