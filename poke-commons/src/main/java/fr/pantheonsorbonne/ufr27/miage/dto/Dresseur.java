package fr.pantheonsorbonne.ufr27.miage.dto;

import java.util.List;

public record Dresseur(int idDresseur, String name, boolean bannedStatus, List<Pokemon> pokedex) {
}
