package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
public class TreatmentSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTreatment", nullable = false)
    private Integer idTreatment;

    @Column(name = "timeTreatment", nullable = false, length = 45)
    private Time timeTreatment;

    @Column(name = "priceTreatment", nullable = false, length = 45)
    private Integer priceTreatment;

    @ManyToOne(cascade = CascadeType.ALL)
    private Dresseur dresseur;

    @ManyToOne(cascade = CascadeType.ALL)
    private Pokemon pokemon;

    public Integer getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(Integer idTreatment) {
        this.idTreatment = idTreatment;
    }

    public Time getTimeTreatment() {
        return timeTreatment;
    }

    public void setTimeTreatment(Time timeTreatment) {
        this.timeTreatment = timeTreatment;
    }

    public Integer getPriceTreatment() {
        return priceTreatment;
    }

    public void setPriceTreatment(Integer priceTreatment) {
        this.priceTreatment = priceTreatment;
    }

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
    }
}
