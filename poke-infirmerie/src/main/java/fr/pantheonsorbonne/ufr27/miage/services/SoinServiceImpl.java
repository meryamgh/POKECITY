package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.RedirectToMairieGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.SoignerPokemonGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.TreatmentDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.TreatmentSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;

@ApplicationScoped
public class SoinServiceImpl implements SoinService{

    @Inject
    TreatmentDAO treatmentDAO;

    @Inject
    SoignerPokemonGateway pokemonGateway;

    @Inject
    RedirectToMairieGateway redirectToMairieGateway;

    @Override
    public Pokemon soignerPokemon(Pokemon pokemon, int idDDresseur) {
        int pokeScore = pokemon.pokeScore();
        pokeScore = pokemon.prix();
        System.out.println("pokescore" + pokeScore);
        System.out.println("prix" + pokemon.prix());
        treatmentDAO.insertTreatmentSession(pokemon, getPriceTreatment(pokemon), idDDresseur);
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
        pokemonGateway.checkBankAccount(pokemon, this.getPriceTreatment(pokemon));
    }


    @Override
    public Collection<TreatmentSession> getAllTreatmentSessionsDresseur(int idDresseur){
        return this.treatmentDAO.getAllTreatmentSessionsByDresseuer(idDresseur);
    }

}
