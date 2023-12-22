package fr.pantheonsorbonne.ufr27.miage.ressources;


import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolSession;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolTicket;
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

    @Inject
    PokemonDao pokemonDao;

    @Path("/SchoolSessions")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<SchoolSession> getAllSchoolSessions() {
        return this.sessionService.getAllSessions();
    }

    @Path("/SchoolTickets")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<SchoolTicket> getAllSchoolTickets() {
        return this.sessionService.getAllTickets();
    }

    @Path("/pokemon/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Pokemon getPokemonById(@PathParam("id") int id) {
        return this.pokemonDao.getPokemonById(id);
    }

    @Path("/SchoolRegister")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response inscrirePokemon() {

        Pokemon pokemon = getPokemonById(1);

        SchoolSession session = sessionService.findRightSession(pokemon);
        sessionService.sendSessionToMairie(session);

        if (this.sessionService.isMoneyEnough()) {
            this.sessionService.inscrirePokemon(pokemon, session);
            return Response.ok().build();
        } else {
            return Response.status(422, "L'inscription n'a pas pu être effectuée.").build();
        }
    }

}