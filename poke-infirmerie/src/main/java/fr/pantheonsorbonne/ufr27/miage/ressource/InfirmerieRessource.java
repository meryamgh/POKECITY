package fr.pantheonsorbonne.ufr27.miage.ressource;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.services.SoinService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/infirmerie")
public class InfirmerieRessource {

    @Inject
    protected SoinService service;

    @Path("{idPokemon}")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response haveEnoughMoney(@PathParam("idPokemon") int idPokemon) {

        if(service.enoughMoney(idPokemon)) {
            service.soignerPokemon();
            return Response.ok().build();
        }else {
            service.redirectToMairie(idPokemon);
            return Response.status(422, "dresseur doesn't have enought money").build();
        }
}
}
