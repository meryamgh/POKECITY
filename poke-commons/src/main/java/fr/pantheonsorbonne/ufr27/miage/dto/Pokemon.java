package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;

public record Pokemon(Integer idPokemon, String name, Integer pokeScore, int prix, String type, boolean isAdopted) implements Serializable {}
