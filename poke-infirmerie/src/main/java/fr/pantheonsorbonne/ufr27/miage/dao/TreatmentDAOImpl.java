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
import java.util.Date;


@ApplicationScoped
public class TreatmentDAOImpl implements TreatmentDAO {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public void insertTreatmentSession(Pokemon pokemon, int treatPrice, int idDresseur) {
        TreatmentSession receipt = new TreatmentSession();
        receipt.setIdPokemon(pokemon.idPokemon());
        receipt.setTimeTreatment(3);
        receipt.setPriceTreatment(treatPrice);
        receipt.setIdDresseur(idDresseur);
        receipt.setTreatmentDate(new Date());
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



    @Override
    public Collection<TreatmentSession> getAllTreatmentSessionsByDresseuer(int idDresseur){
        return em.createQuery("SELECT session FROM TreatmentSession session WHERE session.idDresseur = :idDresseur", TreatmentSession.class)
                .setParameter("idDresseur", idDresseur)
                .getResultList();
    }
}
