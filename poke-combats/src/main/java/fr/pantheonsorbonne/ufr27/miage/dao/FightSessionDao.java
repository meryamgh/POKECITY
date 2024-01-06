package fr.pantheonsorbonne.ufr27.miage.dao;

import java.util.Collection;

import fr.pantheonsorbonne.ufr27.miage.models.FightingSession;

public interface FightSessionDao {

    Collection<FightingSession> getAllFightingSession();

    Collection<FightingSession> getFightingSessionByDresseurId(int idDresseur);

    FightingSession createFightingSession(int idDresseur, int idPokemon, int idOponent, int amountWin, boolean isWinner);

    Collection<FightingSession> getAllFightingSessionByDresseur(int idDresseur);

}
