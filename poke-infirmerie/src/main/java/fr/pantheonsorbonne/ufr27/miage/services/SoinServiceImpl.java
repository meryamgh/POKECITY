package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.RedirectToMairieGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.SoignerPokemonGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.DresseurDao;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.dao.TreatmentDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.TreatmentSession;
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
    DresseurDao dresseurDao;

    @Inject
    SoignerPokemonGateway pokemonGateway;

    @Inject
    RedirectToMairieGateway redirectToMairieGateway;

    @Override
    public boolean enoughMoney(int idPokemon, int priceTreatment) {
        /**Pokemon pokemon = pokemonDao.getPokemonById(idPokemon);
        pokemonGateway.checkBankAccount(pokemon.getPokeScore());**/
        int idDresseur = dresseurDao.getIdDresseurByIdPokemon(idPokemon);
        TreatmentSession treatmentSession = new TreatmentSession(idDresseur, idPokemon, priceTreatment);
        pokemonGateway.checkBankAccount(treatmentSession);
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

    @Override
    public int getPriceTreatment(int idPokemon) {
        int intialPrice = pokemonDao.getPokemonById(idPokemon).getPrix();
        return intialPrice - 10;
    }

}
