package fr.pantheonsorbonne.ufr27.miage.dto;

public record FightSession(Integer idDresseur, Pokemon oponnent, Pokemon ourPokemon, boolean isWinner, int amount) {
}
