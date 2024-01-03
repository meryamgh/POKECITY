package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dao.PokemonStockDao;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;

@ApplicationScoped
public class InventoryPokemonServiceImpl implements InventoryPokemonService{
    @Inject
    PokemonStockDao stockDao;

    @Override
    public Collection<Pokemon> getAllPokemon() {
        return stockDao.getStock();
    }

    @Override
    public Collection<Pokemon> getPokemonByPrice(int price) {
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


}
