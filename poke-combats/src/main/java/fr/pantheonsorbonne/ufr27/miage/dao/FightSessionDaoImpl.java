package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.models.FightingSession;
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
        return em.createQuery("SELECT fight FROM FightingSession fight WHERE fight.idDresseur = :dresseur", FightingSession.class)
                .setParameter("dresseur", idDresseur)
                .getResultList();
    }

    @Override
    @Transactional
    public FightingSession createFightingSession(int idDresseur, int idPokemon, int idOponent, int amountWin, boolean isWinner) {
        FightingSession battle = new FightingSession();
        battle.setIdDresseur(idDresseur);
        battle.setIdPokemon(idPokemon);
        battle.setIdPNJ(idOponent);
        battle.setReward(amountWin);
        battle.setWinner(isWinner);
        em.persist(battle);
        return battle;
    }

}
