package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class SchoolTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSchoolTicket", nullable = false)
    private Integer idSchoolTicket;

    @ManyToOne(cascade = CascadeType.ALL)
    private Dresseur dresseur;

    @ManyToOne(cascade = CascadeType.ALL)
    private Pokemon pokemon;

    @ManyToOne(cascade = CascadeType.ALL)
    private SchoolSession schoolSession;

    public Dresseur getDresseur() {
        return dresseur;
    }

    public void setDresseur(Dresseur dresseur) {
        this.dresseur = dresseur;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;

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
