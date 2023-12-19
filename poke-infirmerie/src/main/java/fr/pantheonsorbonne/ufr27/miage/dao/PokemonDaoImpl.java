package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
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

    @Override
    public Pokemon getPokemonById(int idPokemon) {
        return em.createQuery("SELECT pokemon FROM Pokemon pokemon WHERE pokemon.idPokemon = :id", Pokemon.class)
                .setParameter("id", idPokemon)
                .getSingleResult();
    }


}
