package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class PokemonDaoImpl implements PokemonDao{

    @Inject
    EntityManager em;

    @Override
    public int getPokeScorePokemon() {
        return em.createQuery("SELECT pokeScore FROM Pokemon pokemon", Integer.class).getSingleResult();
    }

    @Override
    public int getPrixPokemon() {
        return em.createQuery("SELECT prix FROM Pokemon pokemon", Integer.class).getSingleResult();
    }

}
