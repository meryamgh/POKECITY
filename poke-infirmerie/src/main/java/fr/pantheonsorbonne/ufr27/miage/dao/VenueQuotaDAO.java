package fr.pantheonsorbonne.ufr27.miage.dao;


import jakarta.transaction.Transactional;
import java.util.Collection;

public interface VenueQuotaDAO {
    @Transactional
    VenueQuota getQuotaForVendorForVenue(int idVendor, int idVenue);

    Collection<Venue> getQuotaForVendor(int idVendor);
}
