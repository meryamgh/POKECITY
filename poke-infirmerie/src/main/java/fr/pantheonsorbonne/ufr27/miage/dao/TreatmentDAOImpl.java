package fr.pantheonsorbonne.ufr27.miage.dao;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class TreatmentDAOImpl implements TreatmentDAO {

    @Inject
    EntityManager em;

    @Override
    public int getPriceTreatment() {
        return em.createQuery("SELECT priceTreatment FROM TreatmentSession treatment", Integer.class).getSingleResult();
    }
}
