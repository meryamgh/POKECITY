package fr.pantheonsorbonne.ufr27.miage.services;


import fr.pantheonsorbonne.ufr27.miage.dao.FightSessionDao;
import fr.pantheonsorbonne.ufr27.miage.dto.Dresseur;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.models.FightingSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;

import static java.lang.Math.abs;

@ApplicationScoped
public class FightSessionServiceImpl implements FightSessionService{

    @Inject
    FightSessionDao fightSessionDao;

    @Inject
    WinnerService winnerService;

    @Override
    public Collection<FightingSession> getAllFights() {
        return this.fightSessionDao.getAllFightingSession();
    }

    @Override
    public FightingSession play(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon dresseurPokemon) {
        //this.fightSessionDao.createFightingSession(,dresseurPokemon, 50, );
        //Pokemon PNJ = getAdversaire();
        Pokemon PNJ = new Pokemon(4, 60, 60, "feu");
        int gain = abs(dresseurPokemon.pokeScore() - PNJ.pokeScore());
        boolean isWinner = winnerService.getWinner(PNJ, dresseurPokemon);
        return this.fightSessionDao.createFightingSession(1, dresseurPokemon.idPokemon(), PNJ.idPokemon(), gain, isWinner);
    }

}
