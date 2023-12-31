package fr.pantheonsorbonne.ufr27.miage.services;


import fr.pantheonsorbonne.ufr27.miage.dao.FightSessionDao;
import fr.pantheonsorbonne.ufr27.miage.models.FightingSession;
import fr.pantheonsorbonne.ufr27.miage.models.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;

@ApplicationScoped
public class FightSessionServiceImpl implements FightSessionService{

    @Inject
    FightSessionDao fightSessionDao;

    @Override
    public Collection<FightingSession> getAllFights() {
        return this.fightSessionDao.getAllFightingSession();
    }

    @Override
    public FightingSession play(Pokemon oponent, Pokemon dresseurPokemon,int idDresseur) {
      //  this.fightSessionDao.createFightingSession(oponent,dresseurPokemon,);
        return null;
    }



}
