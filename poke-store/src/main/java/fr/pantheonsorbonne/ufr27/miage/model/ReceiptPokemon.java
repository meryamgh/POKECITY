package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class ReceiptPokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReceipt", nullable = false)
    private int idReceipt;

    @Column(name = "cost", nullable = false, length = 45)
    private int cost;

    @Column(name = "idDresseur", nullable = false, length = 45)
    private int dresseur;

    @Column(name = "idPokemon", nullable = false, length = 45)
    private int pokemon;

    @Column(name = "datePurchase", nullable = false, length = 45)
    private Date datePurchase;

    public int getIdReceipt() {
        return idReceipt;
    }

    public void setIdReceipt(int idReceipt) {
        this.idReceipt = idReceipt;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(Date datePurchase) {
        this.datePurchase = datePurchase;
    }

    public void setDresseur(int dresseur) {
        this.dresseur = dresseur;
    }

    public int getDresseur(){
        return this.dresseur;
    }

    public int getPokemon() {
        return pokemon;
    }

    public void setPokemon(int pokemon) {
        this.pokemon = pokemon;
    }

}