package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.PurchasePokemonGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonStockDao;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonStockDaoImpl;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;

@ApplicationScoped
public class StoreServiceImpl implements StoreService{

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
    public void deletePokemon(int idPokemon) {
        this.stockDao.deletePokemon(idPokemon);
    }

    @Override
    public void createReceiptPokemon(int idPokemon, int idDresseur) {

    }

    @Inject
    PurchasePokemonGateway pokemonGateway;



    @Override
    public void buyPokemon(int idPokemon) {
        Pokemon pokemonToBuy = this.stockDao.getPokemonById(idPokemon);
        pokemonGateway.checkBankCardDresseur(pokemonToBuy.getIdPokemon(),pokemonToBuy.getPrix());
        System.out.println("icicic ");
    }

}
