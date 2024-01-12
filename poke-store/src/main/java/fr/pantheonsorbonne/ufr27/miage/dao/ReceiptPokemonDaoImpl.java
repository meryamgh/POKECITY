package fr.pantheonsorbonne.ufr27.miage.dao;


import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.ReceiptPokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.Date;


@ApplicationScoped
public class ReceiptPokemonDaoImpl implements ReceiptPokemonDao{

    @PersistenceContext(name = "mysql")
    EntityManager em;


    @Override
    @Transactional
    public void insertReceiptTicket(Pokemon pokemon, int idDresseur) {
        ReceiptPokemon receipt = new ReceiptPokemon();
        receipt.setPokemon(pokemon.idPokemon());
        receipt.setCost(pokemon.pokeScore());
        receipt.setDresseur(idDresseur);
        receipt.setDatePurchase(new Date());
        em.persist(receipt);
        em.flush();

    }

    @Override
    @Transactional
    public Collection<ReceiptPokemon> getAllReceiptsOfStore() {
        return em.createQuery("SELECT receipt FROM ReceiptPokemon receipt", ReceiptPokemon.class).getResultList();
    }



    @Override
    @Transactional
    public Collection<ReceiptPokemon> getAllReceiptsOfStoreByDresseur(int iDresseur) {
        return em.createQuery("SELECT receipt FROM ReceiptPokemon receipt WHERE receipt.dresseur = :idDresseur", ReceiptPokemon.class)
                .setParameter("idDresseur", iDresseur)
                .getResultList();
    }

}
