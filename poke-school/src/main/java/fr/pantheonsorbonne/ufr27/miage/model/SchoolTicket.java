package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class SchoolTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSchoolTicket", nullable = false)
    private int idSchoolTicket;

    @Column(name = "pokemon_idPokemon", nullable = false)
    private int pokemonId;

    @Column(name = "schoolSession_idSchoolSession", nullable = false)
    private int schoolSessionId;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getIdDresseur() {
        return idDresseur;
    }

    public void setIdDresseur(int idDresseur) {
        this.idDresseur = idDresseur;
    }

    @Column(name = "cost", nullable = false)
    private int cost;

    @Column(name = "idDresseur", nullable = false)
    private int idDresseur;

    public int getIdSchoolTicket() {
        return idSchoolTicket;
    }

    public int getSchoolSession_id() {
        return schoolSessionId;
    }

    public void setSchoolSession_id(int schoolSession) {
        this.schoolSessionId = schoolSession;
    }

    public int getPokemon_id() {
        return pokemonId;
    }

    public void setPokemon_id(int pokemon) {
        this.pokemonId = pokemon;
    }
}