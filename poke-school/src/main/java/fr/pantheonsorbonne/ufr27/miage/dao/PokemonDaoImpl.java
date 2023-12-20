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
    public Pokemon getPokemonById(int idPokemon) {
        return em.find(Pokemon.class, idPokemon);
    }

    @Override
    public Integer getPokemonScoreById(int idPokemon) {
        try {
            Integer score = (Integer) em.createQuery("SELECT p.pokeScore FROM Pokemon p WHERE p.id = :idPokemon")
                    .setParameter("idPokemon", idPokemon)
                    .getSingleResult();

            return score;
        } catch (NoResultException e) {
            return null;
        }
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
