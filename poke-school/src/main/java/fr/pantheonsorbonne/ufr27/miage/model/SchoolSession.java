package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class SchoolSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSchoolSession", nullable = false)
    private int idSchoolSession;

    @Column(name = "timeSchoolSession", nullable = false, length = 45, columnDefinition = "INT DEFAULT 6")
    private int timeSchoolSession;


    @Column(name = "priceSchoolSession", nullable = false, length = 45)
    private int priceSchoolSession;

    @Column(name = "pokescoreGain", nullable = false, length = 45)
    private int pokescoreGain;


    public int getIdSchoolSession() {
        return idSchoolSession;
    }

    public void setIdSchoolSession(int idSchoolSession) {
        this.idSchoolSession = idSchoolSession;
    }

    public int getTimeSchoolSession() {
        return timeSchoolSession;
    }

    public void setTimeSchoolSession(int timeSchoolSession) {
        this.timeSchoolSession = timeSchoolSession;
    }

    public int getPriceSchoolSession() {
        return priceSchoolSession;
    }

    public void setPriceSchoolSession(int priceSchoolSession) {
        this.priceSchoolSession = priceSchoolSession;
    }

    public int getPokescoreGain() {
        return pokescoreGain;
    }

    public void setPokescoreGain(int pokescoreGain) {
        this.pokescoreGain = pokescoreGain;
    }
}