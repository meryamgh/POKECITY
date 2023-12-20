package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Dresseur;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

public interface DresseurDao {

    public int getIdDresseurByIdPokemon(int idPokemon);
}
