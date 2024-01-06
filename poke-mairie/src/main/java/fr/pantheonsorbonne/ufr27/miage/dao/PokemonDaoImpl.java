package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;

@ApplicationScoped
public class PokemonDaoImpl implements PokemonDao{

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    @Transactional
    public Pokemon getPokemonById(int idPokemon) {
        return em.find(Pokemon.class, idPokemon);
    }
    @Override
    @Transactional
    public void changeStatus(Pokemon pokemon, int idDresseur, boolean isAdopted) {
        em.createQuery("update Pokemon p set p.isAdopted = :status, p.localisation = :loca where p.idPokemon = :idPokemon")
                .setParameter("status", isAdopted)
                .setParameter("loca", "mairie")
                .setParameter("idPokemon", pokemon.getIdPokemon())
                .executeUpdate();

    }

    @Override
    @Transactional
    public void setPokescorebyId(int id, int newPokescore) {
        Pokemon p = (Pokemon) em.createQuery("SELECT p FROM Pokemon p WHERE p.id = :idPokemon")
                .setParameter("idPokemon", id)
                .getSingleResult();

        p.setPokeScore(newPokescore);
    }

    @Override
    @Transactional
    public void setLocalisation(int idPokemon, String loca) {
        em.createQuery("update Pokemon p set p.localisation = :loca where p.idPokemon = :idPokemon")
                .setParameter("loca", loca)
                .setParameter("idPokemon", idPokemon)
                .executeUpdate();
    }


    @Override
    @Transactional
    public Collection<Pokemon> getAllPokemon() {
        return em.createQuery("SELECT p FROM Pokemon p", Pokemon.class)
                .getResultList();
    }


}
