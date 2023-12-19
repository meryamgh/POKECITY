package fr.pantheonsorbonne.ufr27.miage.ressources;


import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolSession;
import fr.pantheonsorbonne.ufr27.miage.service.SchoolSessionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;

@Path("/pokeschool")
public class SessionSchoolRessource {

    @Inject
    SchoolSessionService sessionService;

    @Path("session")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<SchoolSession> getAllSchoolSessions() {
        return this.sessionService.getAllSessions();
    }

    @Path("registerToSchoolSession")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response registerPokemonToSchoolSession(Pokemon pokemon, SchoolSession course) {

        if ( sessionService.registerPokemon(pokemon, course) ) {
            return Response.ok().build();
        } else {
            return Response.status(422, "L'inscription n'a pas pu être effectuée").build();
        }
    }
}
