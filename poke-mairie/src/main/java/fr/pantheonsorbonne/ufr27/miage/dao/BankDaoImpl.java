package fr.pantheonsorbonne.ufr27.miage.dao;


import fr.pantheonsorbonne.ufr27.miage.model.BankAccount;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BankDaoImpl implements BankDao{

    @PersistenceContext(name = "mysql")
    @Inject
    EntityManager em;



    @Override
    @Transactional
    public BankAccount getBankAccountDresseurBalance(int idDresseur) {
        BankAccount bankCard = em.createQuery("SELECT b FROM BankAccount b WHERE b.dresseur.idDresseur = :id", BankAccount.class)
                .setParameter("id", idDresseur).getSingleResult();

        return bankCard;
    }

}
