package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.exception.NotEnoughMoneyException;
import fr.pantheonsorbonne.ufr27.miage.model.BankAccount;

public interface BankService {

    boolean checkBalance(int amount,int idDresseur);

    BankAccount getCardBank(int idDresseur);

    void creditBankAccount(int amount, int idDresseur);

}
