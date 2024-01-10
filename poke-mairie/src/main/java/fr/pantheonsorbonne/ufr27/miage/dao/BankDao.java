package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.NotEnoughMoneyException;
import fr.pantheonsorbonne.ufr27.miage.model.BankAccount;

public interface BankDao {

    BankAccount getBankAccountDresseur(int idDresseur);

    boolean debitBank(int amount, int idDresseur) throws NotEnoughMoneyException;

    void creditBank(int amount, int idDresseur);

}
