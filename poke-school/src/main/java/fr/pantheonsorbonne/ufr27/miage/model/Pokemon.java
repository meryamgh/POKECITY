package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPokemon", nullable = false)
    private int idPokemon;

    @Column(name = "pokeScore", nullable = false, length = 45)
    private int pokeScore;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }

    public int getPokeScore() {
        return pokeScore;
    }

    public void setPokeScore(int pokeScore) {
        this.pokeScore = pokeScore;
    }


}