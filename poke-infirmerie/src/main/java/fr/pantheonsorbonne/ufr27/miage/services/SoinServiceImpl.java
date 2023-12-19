package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.RedirectToMairieGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.SoignerPokemonGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.dao.TreatmentDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Dresseur;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
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

    @Inject
    RedirectToMairieGateway redirectToMairieGateway;

    @Override
    public boolean enoughMoney(int idPokemon) {
        Pokemon pokemon = pokemonDao.getPokemonById(idPokemon);
        pokemonGateway.checkBankAccount(pokemon.getPokeScore());
        return false;
    }


    @Override
    public void soignerPokemon() {
        int pokeScore = pokemonDao.getPokeScorePokemon();
        pokeScore = pokemonDao.getPrixPokemon();
    }

    @Override
    public void redirectToMairie(int idPokemon) {
        Pokemon pokemon = pokemonDao.getPokemonById(idPokemon);
        redirectToMairieGateway.redirectToMairie(pokemon);
    }

}
