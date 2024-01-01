package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.RedirectToMairieGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.SoignerPokemonGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.TreatmentDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SoinServiceImpl implements SoinService{

    @Inject
    TreatmentDAO treatmentDAO;

    @Inject
    SoignerPokemonGateway pokemonGateway;

    @Inject
    RedirectToMairieGateway redirectToMairieGateway;

    @Override
    public void checkEnoughMoney(Pokemon pokemon) {
        pokemonGateway.checkBankAccount(pokemon);
    }

    @Override
    public Pokemon soignerPokemon(Pokemon pokemon) {
        int pokeScore = pokemon.pokeScore();
        pokeScore = pokemon.prix();
        System.out.println("pokescore" + pokeScore);
        System.out.println("prix" + pokemon.prix());
        treatmentDAO.insertTreatmentSession(pokemon);
        return new Pokemon(pokemon.idPokemon(),pokemon.prix(),pokemon.prix(),pokemon.type(), pokemon.isAdopted(),pokemon.name());
    }

    @Override
    public void redirectToMairie(Pokemon pokemon) {
        redirectToMairieGateway.redirectToMairie(pokemon);
    }

    @Override
    public int getPriceTreatment(Pokemon pokemon) {
        int intialPrice = pokemon.prix();
        System.out.println("initialPrice " + intialPrice);
        return intialPrice / 2;
    }

    @Override
    public void priseEnCharge(Pokemon pokemon){
        checkEnoughMoney(pokemon);
    }

}
