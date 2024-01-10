package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dao.PokemonStockDao;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;
import java.util.Random;

@ApplicationScoped
public class InventoryPokemonServiceImpl implements InventoryPokemonService{
    @Inject
    PokemonStockDao stockDao;

    private static int idGenerate= 15;

    @Override
    public Collection<Pokemon> getAllPokemon() {
        return stockDao.getStock();
    }

    @Override
    public Collection<Pokemon> getPokemonByPrice(int price){
        return stockDao.getStockPokemonByPrice(price);
    }

    @Override
    public void deletePokemon(int idPokemon) throws PokemonNotFoundException  {
        this.stockDao.deletePokemon(idPokemon);
    }

    @Override
    public Pokemon getRandomPokemonFighting() throws PokemonNotFoundException {
        Pokemon pokemonFind = this.stockDao.getRandomPokemon();
        this.stockDao.deletePokemon(pokemonFind.getIdPokemon());
        return pokemonFind;
    }

    @Override
    public void addPokemonToStore(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon) {
        this.stockDao.addPokemonToStore(pokemon);
    }

    @Override
    public fr.pantheonsorbonne.ufr27.miage.dto.Pokemon addNewPokemonToStore() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(100 - 20 + 1) + 20;
        fr.pantheonsorbonne.ufr27.miage.dto.Pokemon p = new fr.pantheonsorbonne.ufr27.miage.dto.Pokemon(idGenerate++,randomNumber,randomNumber,"plante",false,"test");
        this.stockDao.addPokemonToStore(p);
        return p;
    }


}
