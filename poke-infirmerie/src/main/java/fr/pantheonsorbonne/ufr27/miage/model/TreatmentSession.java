package fr.pantheonsorbonne.ufr27.miage.model;


import jakarta.persistence.*;

import java.sql.Time;

@Entity
public class TreatmentSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTreatment", nullable = false)
    private int idTreatment;

    @Column(name = "timeTreatment", nullable = false, length = 45)
    private Time timeTreatment;

    @Column(name = "priceTreatment", nullable = false, length = 45)
    private int priceTreatment;

    @ManyToOne(cascade = CascadeType.ALL)
    private Dresseur dresseur;

    @ManyToOne(cascade = CascadeType.ALL)
    private Pokemon pokemon;

    public int getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(int idTreatment) {
        this.idTreatment = idTreatment;
    }

    public Time getTimeTreatment() {
        return timeTreatment;
    }

    public void setTimeTreatment(Time timeTreatment) {
        this.timeTreatment = timeTreatment;
    }

    public int getPriceTreatment() {
        return priceTreatment;
    }

    public void setPriceTreatment(int priceTreatment) {
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
