package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;


@ApplicationScoped
public class PokemonServiceImpl implements PokemonService{

    @Inject
    PokemonDao pokemonDao;

    @Override
    public void updatePokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon) {
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


}
