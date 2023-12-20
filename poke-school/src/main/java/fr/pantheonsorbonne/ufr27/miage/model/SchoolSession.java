package fr.pantheonsorbonne.ufr27.miage.model;

import com.sun.tools.xjc.model.CDefaultValue;
import jakarta.persistence.*;

import java.sql.Time;

@Entity
public class SchoolSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSchoolSession", nullable = false)
    private Integer idSchoolSession;

    @Column(name = "timeSchoolSession", nullable = false, length = 45, columnDefinition = "INT DEFAULT 6")
    private Integer timeSchoolSession;


    @Column(name = "priceSchoolSession", nullable = false, length = 45)
    private Integer priceSchoolSession;

    @Column(name = "pokescoreGain", nullable = false, length = 45)
    private Integer pokescoreGain;


    public Integer getIdSchoolSession() {
        return idSchoolSession;
    }

    public void setIdSchoolSession(Integer idSchoolSession) {
        this.idSchoolSession = idSchoolSession;
    }

    public Integer getTimeSchoolSession() {
        return timeSchoolSession;
    }

    public void setTimeSchoolSession(Integer timeSchoolSession) {
        this.timeSchoolSession = timeSchoolSession;
    }

    public Integer getPriceSchoolSession() {
        return priceSchoolSession;
    }

    public void setPriceSchoolSession(Integer priceSchoolSession) {
        this.priceSchoolSession = priceSchoolSession;
    }

    public Integer getPokescoreGain() {
        return pokescoreGain;
    }

    public void setPokescoreGain(Integer pokescoreGain) {
        this.pokescoreGain = pokescoreGain;
    }
}
