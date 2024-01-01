package fr.pantheonsorbonne.ufr27.miage.service;

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
    public fr.pantheonsorbonne.ufr27.miage.dto.Pokemon inscrirePokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon) {

        int pokescore= pokemon.pokeScore();
        int idPokemon= pokemon.idPokemon();

        //on récupère l'id de la session
        SchoolSession session = schoolSessionService.findRightSession(pokescore);

        //on crée un ticket pour garder l'historique des cours effectués par un pokemon
        schoolTicketDao.createSchoolTicket(idPokemon, session.getIdSchoolSession());

        //on augmente le score du pokemon qui a suivi le cours
        int gain = session.getPokescoreGain();
        int newScore = pokemon.pokeScore() + gain;
        fr.pantheonsorbonne.ufr27.miage.dto.Pokemon improvedPokemon = new fr.pantheonsorbonne.ufr27.miage.dto.Pokemon(idPokemon, newScore,newScore,pokemon.type(),pokemon.isAdopted(),pokemon.name());

        return improvedPokemon;
    }
}