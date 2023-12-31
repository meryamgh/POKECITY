package fr.pantheonsorbonne.ufr27.miage.dao;

import java.util.Collection;

import fr.pantheonsorbonne.ufr27.miage.models.Dresseur;
import fr.pantheonsorbonne.ufr27.miage.models.FightingSession;
import fr.pantheonsorbonne.ufr27.miage.models.Pokemon;

public interface FightSessionDao {

    Collection<FightingSession> getAllFightingSession();

    Collection<FightingSession> getFightingSessionByDresseurId(int idDresseur);


    FightingSession createFightingSession(Dresseur dresseur, Pokemon pokemon,Pokemon oponent, int amountWin, boolean isWinner);

}
