package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.BankAccount;

public interface BankDao {

    BankAccount getBankAccountDresseurBalance(int idDresseur);

}
