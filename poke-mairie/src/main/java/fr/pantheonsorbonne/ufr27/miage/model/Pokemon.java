package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPokemon", nullable = false)
    private int idPokemon;

    @Column(name = "type", nullable = false, length = 45)
    private String type;

    @Column(name = "pokeScore", nullable = false, length = 45)
    private int pokeScore;

    @Column(name = "isAdopted", nullable = false, length = 45)
    private Boolean isAdopted;

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "localisation", nullable = false, length = 45)
    private String localisation;

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPokeScore() {
        return pokeScore;
    }

    public void setPokeScore(int pokeScore) {
        this.pokeScore = pokeScore;
    }

    public Boolean getAdopted() {
        return isAdopted;
    }

    public void setAdopted(Boolean adopted) {
        isAdopted = adopted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
