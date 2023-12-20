package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDaoImpl;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class PokemonServiceImpl implements PokemonService{

    @Inject
    PokemonDao pokemonDao;
    @Override
    public Pokemon getPokemonById(int idPokemon) {
        return this.pokemonDao.getPokemonById(idPokemon);
    }




}
