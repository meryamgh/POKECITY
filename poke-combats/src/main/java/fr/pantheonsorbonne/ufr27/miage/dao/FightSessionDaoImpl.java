package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.models.Dresseur;
import fr.pantheonsorbonne.ufr27.miage.models.FightingSession;
import fr.pantheonsorbonne.ufr27.miage.models.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;

@ApplicationScoped
public class FightSessionDaoImpl implements FightSessionDao{

    @PersistenceContext(name = "mysql")
    EntityManager em;


    @Override
    @Transactional
    public Collection<FightingSession> getAllFightingSession() {
        return this.em.createQuery("SELECT fight FROM FightingSession fight", FightingSession.class).getResultList();
    }

    @Override
    @Transactional
    public Collection<FightingSession> getFightingSessionByDresseurId(int idDresseur) {
        return em.createQuery("SELECT fight FROM FightingSession fight WHERE fight.dresseur.idDresseur = :dresseur", FightingSession.class)
                .setParameter("dresseur", idDresseur)
                .getResultList();
    }

    @Override
    @Transactional
    public FightingSession createFightingSession(Dresseur dresseur, Pokemon pokemon, Pokemon oponent, int amountWin, boolean isWinner) {
        FightingSession battle = new FightingSession();
        battle.setDresseur(dresseur);
        battle.setPokemonDresseur(pokemon);
        battle.setPokemonPNJ(oponent);
        battle.setReward(amountWin);
        battle.setWinner(isWinner);
        return battle;
    }

}
