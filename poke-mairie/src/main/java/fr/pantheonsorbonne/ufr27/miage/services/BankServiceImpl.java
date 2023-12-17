package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dao.BankDaoImpl;
import fr.pantheonsorbonne.ufr27.miage.dto.TicketDresseurAchat;
import fr.pantheonsorbonne.ufr27.miage.model.BankAccount;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class BankServiceImpl implements BankService{
    @Inject
    BankDaoImpl bankDao;

    @Override
    public TicketDresseurAchat checkBalance(TicketDresseurAchat ticket) {
    BankDaoImpl bankDao = new BankDaoImpl();
        BankAccount card = bankDao.getBankAccountDresseurBalance(ticket.idDresseur());
        if(card.getBalance() <= ticket.prixAchat()){
            card.setBalance(card.getBalance() - ticket.prixAchat());
            return new TicketDresseurAchat(ticket.prixAchat(),ticket.idDresseur(),true);
        }
        return ticket;
    }
}
