package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dao.BankDaoImpl;
import fr.pantheonsorbonne.ufr27.miage.exception.NotEnoughMoneyException;
import fr.pantheonsorbonne.ufr27.miage.model.BankAccount;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class BankServiceImpl implements BankService{
    @Inject
    BankDaoImpl bankDao;


    @Override
    public boolean checkBalance(int amount,int idDresseur) throws NotEnoughMoneyException {

        return this.bankDao.debitBank(amount, idDresseur);

    }

    @Override
    public BankAccount getCardBank(int idDresseur){
        return this.bankDao.getBankAccountDresseur(idDresseur);
    }

    @Override
    public void creditBankAccount(int amount, int idDresseur) {
        this.bankDao.creditBank(amount,idDresseur);
    }


}
