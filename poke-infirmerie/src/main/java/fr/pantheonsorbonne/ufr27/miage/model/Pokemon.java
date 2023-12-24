package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

import java.util.List;

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

    @Column(name = "prix", nullable = false, length = 45)
    private int prix;

    @ManyToOne(cascade = CascadeType.ALL)
    private Dresseur dresseur;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

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
