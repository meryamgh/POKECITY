package fr.pantheonsorbonne.ufr27.miage.models;


import jakarta.persistence.*;
import java.sql.Time;

@Entity
public class FightingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFight", nullable = false)
    private int idFight;

    @Column(name = "isWinner", nullable = false, length = 45)
    private boolean isWinner;

    @Column(name = "reward", nullable = false, length = 45)
    private int reward;

    @Column(name = "idPokemon", nullable = false, length = 45)
    private int idPokemon;

    @Column(name = "idPNJ", nullable = false, length = 45)
    private int idPNJ;

    @Column(name = "idDresseur", nullable = false, length = 45)
    private int idDresseur;

    @Column(name = "timeFight", nullable = false, length = 45)
    private Time timeFight;

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }

    public int getIdFight() {
        return idFight;
    }

    public void setIdFight(int idFight) {
        this.idFight = idFight;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public Time getTimeFight() {
        return timeFight;
    }

    public void setTimeFight(Time timeFight) {
        this.timeFight = timeFight;
    }

    public int getIdPNJ() {
        return idPNJ;
    }

    public void setIdPNJ(int idPNJ) {
        this.idPNJ = idPNJ;
    }

    public int getIdDresseur() {
        return idDresseur;
    }

    public void setIdDresseur(int idDresseur) {
        this.idDresseur = idDresseur;
    }
}