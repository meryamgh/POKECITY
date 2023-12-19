package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dto.TicketDresseurAchat;

public interface BankService {

    boolean checkBalance(int amount,int idDresseur);
}
