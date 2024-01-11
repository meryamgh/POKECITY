package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;

public record FightSession(Integer idDresseur , Pokemon oponnent, Pokemon ourPokemon, boolean isWinner, int amountWin) implements Serializable {
}
