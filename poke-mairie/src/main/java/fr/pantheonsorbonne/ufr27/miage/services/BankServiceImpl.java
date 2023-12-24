package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.BankGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.BankDaoImpl;
import fr.pantheonsorbonne.ufr27.miage.dto.TicketDresseurAchat;
import fr.pantheonsorbonne.ufr27.miage.exception.NotEnoughMoneyException;
import fr.pantheonsorbonne.ufr27.miage.model.BankAccount;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;



@ApplicationScoped
public class BankServiceImpl implements BankService{
    @Inject
    BankDaoImpl bankDao;

    @Inject
    BankGateway bankGateway;

    @Override
    public boolean checkBalance(int amount,int idDresseur) {
        return this.bankDao.debitBank(amount, idDresseur);

    }

    @Override
    public BankAccount getCardBank(int idDresseur){
        return this.bankDao.getBankAccountDresseur(idDresseur);
    }
}
