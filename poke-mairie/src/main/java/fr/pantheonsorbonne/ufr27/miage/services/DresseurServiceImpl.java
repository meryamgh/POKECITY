package fr.pantheonsorbonne.ufr27.miage.services;


import fr.pantheonsorbonne.ufr27.miage.dao.DresseurDao;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.exception.DresseurBannedException;
import fr.pantheonsorbonne.ufr27.miage.exception.NotAvailablePokemonException;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
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



    @Override
    public void affectPokemonToDresseur(int idPokemon, int idDresseur) throws PokemonNotFoundException {
        Pokemon pokemonToAffect = this.pokemonDao.getPokemonById(idPokemon);
        this.pokemonDao.changeStatus(pokemonToAffect,idDresseur,true);
        this.dresseurDao.addPokemonToPokedex(pokemonToAffect,idDresseur);

    }

    @Override
    public Collection<Pokemon> getAllDresseurPokemon(int idDresseur) {
        return this.dresseurDao.getAllPokemons(idDresseur);
    }

    @Override
    public boolean isDresseurOutOfPokemons(int idDresseur) {
        return this.dresseurDao.getNumberPokemon(idDresseur)==0;
    }

    @Override
    public void changeBannedStatus(int idDresseur) {
        this.dresseurDao.setDresseurBannedStatus(idDresseur);
    }

    @Override
    public void deletePokemonDresseur(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon, int idDresseur) throws PokemonNotFoundException {
        Pokemon pokemonToDelete = this.pokemonDao.getPokemonById(pokemon.idPokemon());
        this.dresseurDao.deletePokemon(pokemonToDelete,idDresseur);
    }

    @Override
    public void checkDresseurBanned(int idDresseur) throws DresseurBannedException {
        if(this.dresseurDao.getDresseur(idDresseur).isBannedStatus()){
            throw new DresseurBannedException();
        }
    }

    @Override
    public void checkDresseurCanPlayWithPokemon(int idDresseur, Pokemon pokemon) throws NotAvailablePokemonException {
        if(!this.dresseurDao.isDresseurPokemon(idDresseur,pokemon.getIdPokemon())){
            throw new NotAvailablePokemonException("The pokemon with ID " + pokemon.getIdPokemon() + " is not available for this dresseur.");
        }
        if(!pokemon.getLocalisation().equals("mairie")){
            throw new NotAvailablePokemonException("Pokemon with ID "+pokemon.getIdPokemon()+" is not available because is in the "+pokemon.getLocalisation());
        }
    }

    @Override
    public void checkAvailaibilityToPlay(int idDresseur, Pokemon pokemon) throws DresseurBannedException, NotAvailablePokemonException {
        this.checkDresseurBanned(idDresseur);
        this.checkDresseurCanPlayWithPokemon(idDresseur,pokemon);
    }


}
