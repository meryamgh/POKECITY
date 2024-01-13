package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dao.TreatmentDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.TreatmentSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;
import java.util.logging.Logger;

@ApplicationScoped
public class SoinServiceImpl implements SoinService{

    @Inject
    TreatmentDAO treatmentDAO;;

    private static final Logger LOGGER = Logger.getLogger("logger");


    @Override
    public Pokemon soignerPokemon(Pokemon pokemon, int idDDresseur) {
        treatmentDAO.insertTreatmentSession(pokemon, getPriceTreatment(pokemon), idDDresseur);
        return new Pokemon(pokemon.idPokemon(),pokemon.prix(),pokemon.prix(),pokemon.type(), pokemon.isAdopted(),pokemon.name());
    }

    @Override
    public int getPriceTreatment(Pokemon pokemon) {
        int intialPrice = pokemon.prix();
        LOGGER.info("price : "+pokemon.prix());
        return intialPrice / 2;
    }

    @Override
    public Collection<TreatmentSession> getAllTreatmentSessionsDresseur(int idDresseur){
        return this.treatmentDAO.getAllTreatmentSessionsByDresseuer(idDresseur);
    }

    @Override
    public Collection<TreatmentSession> getAllTreatmentSessions() {
        return treatmentDAO.getAllTreatmentSessions();
    }

}
