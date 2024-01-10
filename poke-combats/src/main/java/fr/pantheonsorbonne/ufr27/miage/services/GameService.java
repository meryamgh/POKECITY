package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.dto.PokemonType;

public interface GameService {

    boolean getWinner(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon oponent, Pokemon ourPokemon);

    boolean percentGiven(int percent);

    int calculateTypeAdvantage(PokemonType ourType, PokemonType opponentType);

    int calculateScoreAdvantage(int ecart);

    int calculateScorePenalty(int ecart);

    int calculateScoreBonus(int ecart);
}
