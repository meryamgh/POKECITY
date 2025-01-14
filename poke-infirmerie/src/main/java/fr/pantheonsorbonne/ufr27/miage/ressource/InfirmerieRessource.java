package fr.pantheonsorbonne.ufr27.miage.ressource;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.TreatmentSession;
import fr.pantheonsorbonne.ufr27.miage.services.SoinService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;

@Path("/infirmerie")
public class InfirmerieRessource {

    @Inject
    protected SoinService service;

    @Path("treatments")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<TreatmentSession> getAllTreatments() {
        return service.getAllTreatmentSessions();
    }

    @Path("treatments/{idDresseur}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<TreatmentSession> getAllTreatmentsByIdDresseur(@PathParam("idDresseur") int idDresseur) {
        return service.getAllTreatmentSessionsDresseur(idDresseur);
    }
}

