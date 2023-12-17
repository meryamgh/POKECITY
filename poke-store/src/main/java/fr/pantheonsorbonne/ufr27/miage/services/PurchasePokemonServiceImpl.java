package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.PurchasePokemonGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonStockDaoImpl;
import fr.pantheonsorbonne.ufr27.miage.dto.TicketDresseurAchat;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.ReceiptPokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PurchasePokemonServiceImpl implements PurchasePokemonService{

    @Inject
    PurchasePokemonGateway pokemonGateway;

    @Inject
    PokemonStockDaoImpl pokemonStockDao;


    @Override
    public void buyPokemon(int idPokemon, int idDresseur) {
        Pokemon pokemonToBuy = this.pokemonStockDao.getPokemonById(idPokemon);
        TicketDresseurAchat ticketAchat = new TicketDresseurAchat(pokemonToBuy.getPrix(),idDresseur,false);
        pokemonGateway.checkBankCardDresseur(ticketAchat);
    }
}
