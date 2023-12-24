package fr.pantheonsorbonne.ufr27.miage.services;


import fr.pantheonsorbonne.ufr27.miage.camel.PokemonGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.DresseurDao;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;

@ApplicationScoped
public class DresseurServiceImpl implements DresseurService{

    @Inject
    PokemonDao pokemonDao;

    @Inject
    DresseurDao dresseurDao;

    @Inject
    PokemonGateway pokemonGateway;


    @Override
    public void affectPokemonToDresseur(int idPokemon, int idDresseur) {
        Pokemon pokemonToAffect = this.pokemonDao.getPokemonById(idPokemon);
        this.pokemonDao.changeStatus(pokemonToAffect,idDresseur,true);
        this.dresseurDao.addPokemonToPokedex(pokemonToAffect,idDresseur);

    }

    @Override
    public Collection<Pokemon> getAllDresseurPokemon(int idDresseur) {
        return this.dresseurDao.getAllPokemons(idDresseur);
    }


}
