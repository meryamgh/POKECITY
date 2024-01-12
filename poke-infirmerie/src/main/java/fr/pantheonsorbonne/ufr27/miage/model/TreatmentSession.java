package fr.pantheonsorbonne.ufr27.miage.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;


@Entity
public class TreatmentSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTreatment", nullable = false)
    private int idTreatment;

    @Column(name = "timeTreatment", nullable = false, length = 45)
    private int timeTreatment;

    @Column(name = "priceTreatment", nullable = false, length = 45)
    private int priceTreatment;

    @Column(name = "idPokemon", nullable = false, length = 45)
    private int idPokemon;

    public int getIdDresseur() {
        return idDresseur;
    }

    public void setIdDresseur(int idDresseur) {
        this.idDresseur = idDresseur;
    }

    @Column(name = "idDresseur", nullable = false, length = 45)
    private int idDresseur;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    @Column(name = "treatmentDate", nullable = false, length = 45)
    private Date treatmentDate;

    public Date getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentDate(Date datePurchase) {
        this.treatmentDate = datePurchase;
    }

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }

    public int getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(int idTreatment) {
        this.idTreatment = idTreatment;
    }

    public int getTimeTreatment() {
        return timeTreatment;
    }

    public void setTimeTreatment(int timeTreatment) {
        this.timeTreatment = timeTreatment;
    }

    public int getPriceTreatment() {
        return priceTreatment;
    }

    public void setPriceTreatment(int priceTreatment) {
        this.priceTreatment = priceTreatment;
    }
}
