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
        this.em.merge(dresseur);
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
    @Transactional
    public boolean isDresseurPokemon(int idDresseur, int idPokemon)   {
        Collection<Pokemon> allDresseurPokemons = this.getAllPokemons(idDresseur);
        return allDresseurPokemons.stream()
                .anyMatch(pokemon -> pokemon.getIdPokemon() == idPokemon);
    }

    @Override
    @Transactional
    public int getNumberPokemon(int idDresseur) {
        Long count = em.createQuery("SELECT COUNT(p) FROM Pokedex p WHERE p.dresseur.idDresseur = :id", Long.class)
                .setParameter("id", idDresseur)
                .getSingleResult();
        return count.intValue();
    }

    @Override
    @Transactional
    public void setDresseurBannedStatus(int idDresseur) {
        em.createQuery("UPDATE Dresseur d SET d.bannedStatus = true WHERE d.idDresseur = :id")
                .setParameter("id", idDresseur)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void deletePokemon(Pokemon pokemon, int idDresseur) {
        this.em.createQuery("DELETE FROM Pokedex WHERE pokemon.idPokemon = : idPokemon")
                .setParameter("idPokemon", pokemon.getIdPokemon())
                .executeUpdate();

    }

}
