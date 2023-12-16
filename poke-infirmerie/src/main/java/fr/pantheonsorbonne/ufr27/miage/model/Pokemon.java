package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPokemon", nullable = false)
    private Integer idPokemon;

    @Column(name = "pokeScore", nullable = false, length = 45)
    private Integer pokeScore;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    public Integer getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(Integer idPokemon) {
        this.idPokemon = idPokemon;
    }

    public Integer getPokeScore() {
        return pokeScore;
    }

    public void setPokeScore(Integer pokeScore) {
        this.pokeScore = pokeScore;
    }

}
