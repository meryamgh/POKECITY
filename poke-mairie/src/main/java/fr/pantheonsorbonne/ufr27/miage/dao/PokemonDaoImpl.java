package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PokemonDaoImpl implements PokemonDao{

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    @Transactional
    public Pokemon getPokemonById(int idPokemon) {
        return em.createQuery("SELECT poke FROM Pokemon poke WHERE poke.idPokemon = :id", Pokemon.class)
                .setParameter("id", idPokemon).getSingleResult();
    }

    @Override
    @Transactional
    public void changeStatus(Pokemon pokemon, int idDresseur, boolean isAdopted) {
        em.createQuery("update Pokemon p set p.isAdopted = :status where p.idPokemon = :idPokemon")
                .setParameter("status", isAdopted)
                .setParameter("idPokemon", pokemon.getIdPokemon())
                .executeUpdate();
    }

}