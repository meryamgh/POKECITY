package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.models.FightingSession;
import fr.pantheonsorbonne.ufr27.miage.models.Pokemon;

import java.util.Collection;

public interface FightSessionService {

    Collection<FightingSession> getAllFights();

    FightingSession play(Pokemon oponent, Pokemon dresseurPokemon,int idDresseur);





}
