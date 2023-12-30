package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


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


}
