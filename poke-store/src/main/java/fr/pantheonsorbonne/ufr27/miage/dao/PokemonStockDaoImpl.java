package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class PokemonStockDaoImpl implements PokemonStockDao{

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    @Transactional
    public Collection<Pokemon> getStock(){
        return em.createQuery("SELECT poke FROM Pokemon poke", Pokemon.class).getResultList();
    }

    @Override
    @Transactional
    public Collection<Pokemon> getStockPokemonByPrice(int price){
        return em.createQuery("SELECT poke FROM Pokemon poke WHERE poke.prix <= :price", Pokemon.class)
                .setParameter("price", price)
                .getResultList();
    }

    @Override
    @Transactional
    public Pokemon getPokemonById(int idPokemon) throws PokemonNotFoundException {
        try {
            return em.createQuery("SELECT poke FROM Pokemon poke WHERE poke.idPokemon = :id", Pokemon.class)
                    .setParameter("id", idPokemon).getSingleResult();
        } catch (NoResultException e) {
            throw new PokemonNotFoundException(idPokemon);
        }
    }



    @Override
    @Transactional
    public void deletePokemon(int idPokemon) throws PokemonNotFoundException {
        Pokemon pokemonToDelete = getPokemonById(idPokemon);
        if (pokemonToDelete != null) {
            em.remove(pokemonToDelete);
        }
    }

    @Override
    public Pokemon getRandomPokemon(){
        Collection<Pokemon> allAvailablePokemon = this.getStock();
        List<Pokemon> availablePokemonList = List.copyOf(allAvailablePokemon);
        int randomIndex = new Random().nextInt(availablePokemonList.size());
        return availablePokemonList.get(randomIndex);
    }

    @Override
    @Transactional
    public void addPokemonToStore(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon) {
        Pokemon pokemonToAdd = new Pokemon();
        pokemonToAdd.setName(pokemon.name());
        pokemonToAdd.setIdPokemon(pokemon.idPokemon());
        pokemonToAdd.setPrix(pokemon.prix());
        pokemonToAdd.setType(pokemon.type());
        this.em.merge(pokemonToAdd);
      }


}
