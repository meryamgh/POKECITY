package fr.pantheonsorbonne.ufr27.miage.services;


import fr.pantheonsorbonne.ufr27.miage.dao.FightSessionDao;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.models.FightingSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;


@ApplicationScoped
public class FightSessionServiceImpl implements FightSessionService{

    @Inject
    FightSessionDao fightSessionDao;

    @Inject
    GameService gameService;

    @Override
    public Collection<FightingSession> getAllFights() {
        return this.fightSessionDao.getAllFightingSession();
    }

    @Override
    public FightingSession play(Pokemon oponent, Pokemon ourPokemon, int idDresseur) {
        int gain = oponent.pokeScore();
        boolean isWinner = gameService.getWinner(oponent, ourPokemon);
        return this.fightSessionDao.createFightingSession(idDresseur, ourPokemon.idPokemon(), oponent.idPokemon(), gain, isWinner);
    }

    @Override
    public Collection<FightingSession> getAllFightsDresseur(int idDresseur){
        return this.fightSessionDao.getAllFightingSessionByDresseur(idDresseur);
    }

}
