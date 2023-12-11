package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Dresseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDresseur", nullable = false)
    private Integer idDresseur;

    @Column(name = "name", nullable = false, length = 45)
    private String name;


    public Integer getIdDresseur() {
        return idDresseur;
    }

    public void setIdDresseur(Integer idDresseur) {
        this.idDresseur = idDresseur;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
