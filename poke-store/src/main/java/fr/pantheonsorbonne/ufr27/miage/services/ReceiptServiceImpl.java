package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.PurchasePokemonGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonStockDao;
import fr.pantheonsorbonne.ufr27.miage.dao.ReceiptPokemonDao;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.ReceiptPokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;

@ApplicationScoped
public class ReceiptServiceImpl implements ReceiptService{
    @Inject
    PokemonStockDao stockDao;

    @Inject
    PurchasePokemonGateway pokemonGateway;

    @Inject
    InventoryPokemonService inventoryPokemonService;

    @Inject
    ReceiptPokemonDao receiptPokemonDao;


    @Override
    public void createReceiptPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon idPokemon, int idDresseur) {
        this.receiptPokemonDao.insertReceiptTicket(idPokemon,idDresseur);
    }

    @Override
    public void pokemonSalled(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon, int idDresseur) throws PokemonNotFoundException {
        this.inventoryPokemonService.deletePokemon(pokemon.idPokemon());
        this.createReceiptPokemon(pokemon,idDresseur);
    }

    @Override
    public Collection<ReceiptPokemon> getAllReceipts() {
        return this.receiptPokemonDao.getAllReceiptsOfStore();
    }




    @Override
    public void buyPokemon(int idPokemon)  {
        Pokemon pokemonToBuy = this.stockDao.getPokemonById(idPokemon);
        pokemonGateway.checkBankCardDresseur(pokemonToBuy.getIdPokemon(),pokemonToBuy.getPrix());
        System.out.println("icicic ");
    }
}
