package fr.pantheonsorbonne.ufr27.miage.dao;


import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.Collection;

@ApplicationScoped
public class PokemonStockDaoImpl implements PokemonStockDao{

    @Inject
    EntityManager em;

    @Override
    public Collection<Pokemon> getStock() {
        return em.createQuery("SELECT poke FROM Pokemon poke", Pokemon.class).getResultList();
    }

    @Override
    public Collection<Pokemon> getStockPokemonByPrice(int price) {
        return em.createQuery("SELECT poke FROM Pokemon poke WHERE poke.prix <= :price", Pokemon.class)
                .setParameter("price", price)
                .getResultList();
    }

    @Override
    public Pokemon getPokemonById(int idPokemon) {
        return em.createQuery("SELECT poke FROM Pokemon poke WHERE poke.idPokemon = :id", Pokemon.class)
                .setParameter("id", idPokemon).getSingleResult();
    }


}
