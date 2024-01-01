package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;

public interface WinnerService {

    public boolean getWinner(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon oponent, Pokemon ourPokemon);

    public boolean percentGiven(int percent);
}
