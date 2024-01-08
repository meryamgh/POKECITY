package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;

public interface GameService {

    boolean getWinner(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon oponent, Pokemon ourPokemon);

    boolean percentGiven(int percent);
}
