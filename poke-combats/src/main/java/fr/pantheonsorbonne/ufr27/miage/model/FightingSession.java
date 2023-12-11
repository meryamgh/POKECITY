package fr.pantheonsorbonne.ufr27.miage.model;


import jakarta.persistence.*;
import java.sql.Time;

@Entity
public class FightingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFight", nullable = false)
    private Integer idFight;

    @Column(name = "isWinner", nullable = false, length = 45)
    private boolean isWinner;

    @Column(name = "reward", nullable = false, length = 45)
    private Integer reward;


    @ManyToOne(cascade = CascadeType.ALL)
    private Dresseur dresseur;

    @ManyToOne(cascade = CascadeType.ALL)
    private Pokemon pokemonDresseur;

    @ManyToOne(cascade = CascadeType.ALL)
    private Pokemon pokemonPNJ;

    @Column(name = "timeFight", nullable = false, length = 45)
    private Time timeFight;

    public Integer getIdFight() {
        return idFight;
    }

    public void setIdFight(Integer idFight) {
        this.idFight = idFight;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Dresseur getDresseur() {
        return dresseur;
    }

    public void setDresseur(Dresseur dresseur) {
        this.dresseur = dresseur;
    }

    public Pokemon getPokemonDresseur() {
        return pokemonDresseur;
    }

    public void setPokemonDresseur(Pokemon pokemonDresseur) {
        this.pokemonDresseur = pokemonDresseur;
    }

    public Pokemon getPokemonPNJ() {
        return pokemonPNJ;
    }

    public void setPokemonPNJ(Pokemon pokemonPNJ) {
        this.pokemonPNJ = pokemonPNJ;
    }

    public Time getTimeFight() {
        return timeFight;
    }

    public void setTimeFight(Time timeFight) {
        this.timeFight = timeFight;
    }

}