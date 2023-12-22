package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;

public record Pokemon(int idPokemon, int pokeScore,int prix) implements Serializable {}
