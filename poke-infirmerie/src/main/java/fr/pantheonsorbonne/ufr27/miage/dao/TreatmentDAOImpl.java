package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.TreatmentSession;
import fr.pantheonsorbonne.ufr27.miage.services.SoinService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.sql.Time;
import java.util.Collection;


@ApplicationScoped
public class TreatmentDAOImpl implements TreatmentDAO {

    @Inject
    EntityManager em;

    @Inject
    SoinService service;

    @Override
    @Transactional
    public void insertTreatmentSession(Pokemon pokemon) {
        TreatmentSession receipt = new TreatmentSession();
        receipt.setIdPokemon(pokemon.idPokemon());
        receipt.setTimeTreatment(3);
        receipt.setPriceTreatment(service.getPriceTreatment(pokemon));
        em.persist(receipt);
        em.flush();

    }

    @Override
    public Collection<TreatmentSession> getAllTreatmentSessions() {
        return em.createQuery("SELECT session FROM TreatmentSession session", TreatmentSession.class).getResultList();
    }

    @Override
    public String getTimeTreatmentAsString(Time timeTreatment) {
        return timeTreatment.toString();
    }
}
