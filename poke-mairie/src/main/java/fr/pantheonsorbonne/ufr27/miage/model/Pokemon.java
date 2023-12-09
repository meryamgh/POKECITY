package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPokemon", nullable = false)
    private Integer idPokemon;

    @Column(name = "type", nullable = false, length = 45)
    private String type;

    @Column(name = "pokeScore", nullable = false, length = 45)
    private Integer pokeScore;

    @Column(name = "isAdopted", nullable = false, length = 45)
    private Boolean isAdopted;

    public Integer getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(Integer idPokemon) {
        this.idPokemon = idPokemon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPokeScore() {
        return pokeScore;
    }

    public void setPokeScore(Integer pokeScore) {
        this.pokeScore = pokeScore;
    }

    public Boolean getAdopted() {
        return isAdopted;
    }

    public void setAdopted(Boolean adopted) {
        isAdopted = adopted;
    }


}
