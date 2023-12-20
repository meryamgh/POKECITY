package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;

public record TreatmentSession(int idDresseur, int idPokemon, int priceTreatment) implements Serializable {
}
