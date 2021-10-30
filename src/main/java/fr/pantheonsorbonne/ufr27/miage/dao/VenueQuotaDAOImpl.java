package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.Gig;
import fr.pantheonsorbonne.ufr27.miage.model.Venue;
import fr.pantheonsorbonne.ufr27.miage.model.VenueLineUp;
import fr.pantheonsorbonne.ufr27.miage.model.VenueQuota;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@RequestScoped
public class VenueQuotaDAOImpl implements VenueQuotaDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public VenueQuota getQuotaForVendorForVenue(int idVendor, int idVenue) {
        return em.createNamedQuery("getQuotaForVendorForVenue", VenueQuota.class).setParameter("idVendor", idVendor).setParameter("idVenue", idVenue).getSingleResult();
    }

    @Override
    public Collection<Venue> getQuotaForVendor(int idVendor) {
        return em.createNamedQuery("getAvailableVenuesForVendor", Venue.class).setParameter("idVendor", idVendor).getResultList();
    }
}
