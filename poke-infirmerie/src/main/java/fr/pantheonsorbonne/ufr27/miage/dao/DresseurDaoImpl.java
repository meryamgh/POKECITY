package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class DresseurDaoImpl implements DresseurDao{

    @Inject
    EntityManager em;

    @Override
    public int getIdDresseurByIdPokemon(int idPokemon) {
        return em.createQuery("SELECT dresseur.idDresseur FROM Pokemon pokemon WHERE pokemon.idPokemon = :id", int.class)
                .setParameter("id", idPokemon)
                .getSingleResult();
    }
}
