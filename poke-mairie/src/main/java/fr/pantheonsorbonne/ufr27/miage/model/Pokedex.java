package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;


@Entity
@Table(name = "Dresseur_Pokemon")
public class Pokedex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "Dresseur_idDresseur")
    private Dresseur dresseur;

    @OneToOne
    @JoinColumn(name = "pokedex_idPokemon", unique = true)
    private Pokemon pokemon;

    public Dresseur getDresseur() {
        return dresseur;
    }

    public void setDresseur(Dresseur dresseur) {
        this.dresseur = dresseur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
