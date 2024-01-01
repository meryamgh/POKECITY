package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;

public record Pokemon(Integer idPokemon, Integer pokeScore, int prix, String type, boolean isAdopted, String name) implements Serializable {}
