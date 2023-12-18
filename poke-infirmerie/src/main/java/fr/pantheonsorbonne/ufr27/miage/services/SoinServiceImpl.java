package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.SoignerPokemonGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.dao.TreatmentDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Dresseur;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import jakarta.inject.Inject;

public class SoinServiceImpl implements SoinService{

    @Inject
    PokemonDao pokemonDao;

    @Inject
    TreatmentDAO treatmentDAO;

    @Inject
    SoignerPokemonGateway pokemonGateway;

    @Override
    public boolean enoughMoney(Pokemon pokemon) {
        if(treatmentDAO.getPriceTreatment() > )
        pokemonGateway.checkBankAccount(pokemon.pokeScore());
    }

    @Override
    public void soignerPokemon() {
        int pokeScore = pokemonDao.getPokeScorePokemon();
        pokeScore = pokemonDao.getPrixPokemon();
    }

    @Override
    public void redirectToMairie() {

    }
}
