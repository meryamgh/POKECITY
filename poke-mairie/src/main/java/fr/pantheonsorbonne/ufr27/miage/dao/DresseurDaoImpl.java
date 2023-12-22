package fr.pantheonsorbonne.ufr27.miage.dao;


import fr.pantheonsorbonne.ufr27.miage.model.Dresseur;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;

@ApplicationScoped
public class DresseurDaoImpl implements DresseurDao{

    @PersistenceContext(name = "mysql")
    EntityManager em;
    @Override
    @Transactional
    public void addPokemonToPokedex(Pokemon idPokemon, int idDresseur) {
        Dresseur dresseur = this.getDresseur(idDresseur);
        dresseur.getPokedex().add(idPokemon);

    }

    @Override
    @Transactional
    public Dresseur getDresseur(int idDresseur) {
        return em.createQuery("SELECT dre FROM Dresseur dre WHERE dre.idDresseur = :id", Dresseur.class)
                .setParameter("id", idDresseur).getSingleResult();
    }

    @Override
    @Transactional
    public Collection<Pokemon> getAllPokemons(int idDresseur) {
        return em.createQuery("SELECT dre.pokedex FROM Dresseur dre WHERE dre.idDresseur = :id", Pokemon.class)
                .setParameter("id", idDresseur)
                .getResultList();
    }

    @Override
    public boolean checkRightDresseur(Dresseur dresseur){

    }
}
