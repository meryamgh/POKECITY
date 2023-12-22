package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Dresseur;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

import java.util.Collection;

public interface DresseurDao {

    void addPokemonToPokedex(Pokemon idPokemon, int idDresseur);

    Dresseur getDresseur(int idDresseur);

    Collection<Pokemon> getAllPokemons(int idDresseur);

    boolean checkRightDresseur(Dresseur Dresseur);

}
