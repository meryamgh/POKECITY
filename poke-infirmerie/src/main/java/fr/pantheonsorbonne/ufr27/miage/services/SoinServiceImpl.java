package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.RedirectToMairieGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.SoignerPokemonGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.DresseurDao;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.dao.TreatmentDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.dto.TreatmentSession;
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
    public void checkEnoughMoney(int idPokemon, int priceTreatment) {
        pokemonGateway.checkBankAccount(idPokemon,priceTreatment);
    }

    @Override
    public void soignerPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon) {
        int pokeScore = pokemon.pokeScore();
        pokeScore = pokemon.prix();
    }

    @Override
    public void redirectToMairie(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon) {
        redirectToMairieGateway.redirectToMairie(pokemon);
    }

    @Override
    public int getPriceTreatment(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon) {
        int intialPrice = pokemon.prix();
        return intialPrice / 2;
    }

}
