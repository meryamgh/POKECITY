package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.models.FightingSession;

import java.util.Collection;

public interface FightSessionService {

    Collection<FightingSession> getAllFights();

    FightingSession play(Pokemon oponent, Pokemon ourPokemon, int idDresseur);

}
