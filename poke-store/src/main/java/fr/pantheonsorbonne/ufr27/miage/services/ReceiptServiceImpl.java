package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.gateways.BankGateway;
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
    InventoryPokemonService inventoryPokemonService;

    @Inject
    ReceiptPokemonDao receiptPokemonDao;

    @Inject
    BankGateway bankGateway;


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
    public Collection<ReceiptPokemon> getAllReceiptsByDresseur(int idDresseur) {
        return this.receiptPokemonDao.getAllReceiptsOfStoreByDresseur(idDresseur);
    }




    @Override
    public void buyPokemon(int idPokemon) throws PokemonNotFoundException {
        Pokemon pokemonToBuy = this.stockDao.getPokemonById(idPokemon);
        bankGateway.checkBankCardDresseur(pokemonToBuy);
    }
}
