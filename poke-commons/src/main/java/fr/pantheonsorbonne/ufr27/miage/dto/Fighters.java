package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;

public record Fighters(Pokemon oponnent, Pokemon ourPokemon) implements Serializable {
}
