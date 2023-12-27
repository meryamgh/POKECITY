package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;

public record Pokemon(Integer idPokemon, Integer pokeScore, int prix) implements Serializable {}
