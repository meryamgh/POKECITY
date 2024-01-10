package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;


@ApplicationScoped
public class PokemonServiceImpl implements PokemonService{

    @Inject
    PokemonDao pokemonDao;

    @Override
    public void updatePokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon) throws PokemonNotFoundException {
        int id = pokemon.idPokemon();
        pokemonDao.getPokemonById(id);
        int newScore = pokemon.pokeScore();
        pokemonDao.setPokescorebyId(id, newScore);
    }

    @Override
    public void updatePokemonLocalisation(int idPokemon, String newLocalisation) {
        this.pokemonDao.setLocalisation(idPokemon,newLocalisation);
    }

    @Override
    public Collection<Pokemon> getAllPokemon(){
        return this.pokemonDao.getAllPokemon();
    }

    @Override
    public Collection<Pokemon> getPokemonByLocalisation(String localisation) {
        return this.pokemonDao.getPokemonByLocation(localisation);
    }

    @Override
    public void addNewPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon) {
        Pokemon p =new Pokemon();
        p.setType(pokemon.type());
        p.setIdPokemon(pokemon.idPokemon());
        p.setName(pokemon.name());
        p.setAdopted(false);
        p.setPokeScore(pokemon.pokeScore());
        p.setLocalisation("store");

        this.pokemonDao.addNewPokemon(p);
    }


}
