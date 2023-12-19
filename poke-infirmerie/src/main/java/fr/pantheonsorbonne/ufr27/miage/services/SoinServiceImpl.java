package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.SoignerPokemonGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.dao.TreatmentDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Dresseur;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SoinServiceImpl implements SoinService{

    @Inject
    PokemonDao pokemonDao;

    @Inject
    TreatmentDAO treatmentDAO;

    @Inject
    SoignerPokemonGateway pokemonGateway;

    @Override
    public boolean enoughMoney(Pokemon pokemon) {
        pokemonGateway.checkBankAccount(pokemon.pokeScore());
        return false;
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
