package fr.pantheonsorbonne.ufr27.miage.ressource;

import fr.pantheonsorbonne.ufr27.miage.dao.TreatmentDAO;
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

    @Inject
    TreatmentDAO treatmentDAO;

    @Path("{idPokemon}")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response treatPokemon(@PathParam("idPokemon") int idPokemon) {
        fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon = new Pokemon(idPokemon, 0, 70);
        service.priseEnCharge(pokemon);
        System.out.println("haveEnoughMoney" + pokemon.prix());
        return Response.ok().build();
    }

    @Path("treatments")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<TreatmentSession> getAllTreatments() {
        return treatmentDAO.getAllTreatmentSessions();
    }
}

