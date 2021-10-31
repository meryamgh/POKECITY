package fr.pantheonsorbonne.ufr27.miage.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VenueQuotaId implements Serializable {
    @ManyToOne
    private Venue venue;

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @ManyToOne
    private Vendor vendor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VenueQuotaId that = (VenueQuotaId) o;
        return venue.equals(that.venue) && vendor.equals(that.vendor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(venue, vendor);
    }
}