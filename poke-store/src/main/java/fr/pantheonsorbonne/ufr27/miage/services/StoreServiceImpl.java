package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.PurchasePokemonGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonStockDao;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonStockDaoImpl;
import fr.pantheonsorbonne.ufr27.miage.dao.ReceiptPokemonDao;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.ReceiptPokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;

@ApplicationScoped
public class StoreServiceImpl implements StoreService{

    @Inject
    PokemonStockDao stockDao;

    @Inject
    ReceiptPokemonDao receiptPokemonDao;

    @Override
    public Collection<Pokemon> getAllPokemon() {
        return stockDao.getStock();
    }

    @Override
    public Collection<Pokemon> getPokemonByPrice(int price) {
        return stockDao.getStockPokemonByPrice(price);
    }

    @Override
    public void deletePokemon(int idPokemon)  throws PokemonNotFoundException{
        this.stockDao.deletePokemon(idPokemon);
    }

    @Override
    public void createReceiptPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon idPokemon, int idDresseur) {
        this.receiptPokemonDao.insertReceiptTicket(idPokemon,idDresseur);
    }

    @Override
    public void pokemonSalled(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon, int idDresseur) throws PokemonNotFoundException{
        this.deletePokemon(pokemon.idPokemon());
        this.createReceiptPokemon(pokemon,idDresseur);
    }

    @Override
    public Collection<ReceiptPokemon> getAllReceipts() {
        return this.receiptPokemonDao.getAllReceiptsOfStore();
    }

    @Inject
    PurchasePokemonGateway pokemonGateway;



    @Override
    public void buyPokemon(int idPokemon) throws PokemonNotFoundException {
        Pokemon pokemonToBuy = this.stockDao.getPokemonById(idPokemon);
        pokemonGateway.checkBankCardDresseur(pokemonToBuy.getIdPokemon(),pokemonToBuy.getPrix());
        System.out.println("icicic ");
    }

}
