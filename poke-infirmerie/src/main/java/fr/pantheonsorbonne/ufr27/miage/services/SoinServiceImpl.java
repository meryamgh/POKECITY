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
    public void checkEnoughMoney(int idPokemon, int price, int pokescore, int pricetreatment, String type, boolean isAdopted, String name) {
        pokemonGateway.checkBankAccount(idPokemon, name, price, pokescore, pricetreatment, type, isAdopted);
    }

    @Override
    public fr.pantheonsorbonne.ufr27.miage.dto.Pokemon soignerPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon) {
        int pokeScore = pokemon.pokeScore();
        pokeScore = pokemon.prix();
        System.out.println("pokescore" + pokeScore);
        System.out.println("prix" + pokemon.prix());
        treatmentDAO.insertTreatmentSession(pokemon);
        return new Pokemon(pokemon.idPokemon(),pokemon.prix(),pokemon.prix(), pokemon.type(), pokemon.isAdopted(), pokemon.name());
    }

    @Override
    public void redirectToMairie(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon) {
        redirectToMairieGateway.redirectToMairie(pokemon);
    }

    @Override
    public int getPriceTreatment(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon) {
        int intialPrice = pokemon.prix();
        System.out.println("initialPrice " + intialPrice);
        return intialPrice / 2;
    }

    @Override
    public void priseEnCharge(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon){
        checkEnoughMoney(pokemon.idPokemon(), pokemon.prix(), pokemon.pokeScore(), this.getPriceTreatment(pokemon), pokemon.type(), pokemon.isAdopted(), pokemon.name());
    }

}
