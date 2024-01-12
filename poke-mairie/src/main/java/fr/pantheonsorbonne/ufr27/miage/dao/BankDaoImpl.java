package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.BankAccount;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BankDaoImpl implements BankDao{

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    @Transactional
    public BankAccount getBankAccountDresseur(int idDresseur) {

        return em.createQuery("SELECT b FROM BankAccount b WHERE b.dresseur.idDresseur = :id", BankAccount.class)
                .setParameter("id", idDresseur).getSingleResult();
    }

    @Override
    @Transactional
    public boolean debitBank(int amount, int idDresseur) {
        BankAccount balance = this.getBankAccountDresseur(idDresseur);
        if(balance.getBalance()>=amount){
            balance.setBalance(balance.getBalance() - amount);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void creditBank(int amount, int idDresseur) {
        BankAccount balance = this.getBankAccountDresseur(idDresseur);
        balance.setBalance(balance.getBalance() + amount);
        this.em.persist(balance);
    }


}
