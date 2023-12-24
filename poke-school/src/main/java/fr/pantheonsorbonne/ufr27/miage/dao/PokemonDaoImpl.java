package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PokemonDaoImpl implements PokemonDao{

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public Pokemon getPokemonById(int idPokemon) {
        return em.find(Pokemon.class, idPokemon);
    }

    @Override
    @Transactional
    public int getPokemonScoreById(int idPokemon) {

            int score = (int) em.createQuery("SELECT p.pokeScore FROM Pokemon p WHERE p.id = :idPokemon")
                    .setParameter("idPokemon", idPokemon)
                    .getSingleResult();

            return score;

    }


    @Override
    @Transactional
    public void setPokescorebyId(int id, int newPokescore) {
        Pokemon p = (Pokemon) em.createQuery("SELECT p FROM Pokemon p WHERE p.id = :idPokemon")
                .setParameter("idPokemon", id)
                .getSingleResult();

        p.setPokeScore(newPokescore);
    }


}
