package fr.pantheonsorbonne.ufr27.miage.models;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
public class Dresseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDresseur", nullable = false)
    private int idDresseur;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    public int getIdDresseur() {
        return idDresseur;
    }

    public void setIdDresseur(int idDresseur) {
        this.idDresseur = idDresseur;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}