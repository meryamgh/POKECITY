package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dao.SchoolTicketDao;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RegistrationServiceImpl implements RegistrationService {

    @Inject
    SchoolTicketDao schoolTicketDao;

    @Inject
    SchoolSessionService schoolSessionService;


    @Transactional
    @Override
    public fr.pantheonsorbonne.ufr27.miage.dto.Pokemon inscrirePokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon, int idDresseur) {

        int pokescore= pokemon.pokeScore();
        int idPokemon= pokemon.idPokemon();

        SchoolSession session = schoolSessionService.findRightSession(pokescore);

        schoolTicketDao.createSchoolTicket(idPokemon, session.getIdSchoolSession(), session.getPriceSchoolSession(), idDresseur);

        int gain = session.getPokescoreGain();
        int newScore = pokemon.pokeScore() + gain;

        return new fr.pantheonsorbonne.ufr27.miage.dto.Pokemon(idPokemon, newScore,newScore,pokemon.type(),pokemon.isAdopted(),pokemon.name());
    }
}