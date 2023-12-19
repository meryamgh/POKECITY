package fr.pantheonsorbonne.ufr27.miage.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class TreatmentDAOImpl implements TreatmentDAO {

    @Inject
    EntityManager em;

    @Override
    public int getPriceTreatment() {
        return em.createQuery("SELECT priceTreatment FROM TreatmentSession treatment", Integer.class).getSingleResult();
    }
}
