package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class SchoolTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSchoolTicket", nullable = false)
    private Integer idSchoolTicket;

    @Column(name = "pokemon_idPokemon", nullable = false)
    private int pokemonId;

    @Column(name = "schoolSession_idSchoolSession", nullable = false)
    private int schoolSessionId;

    public Integer getIdSchoolTicket() {
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