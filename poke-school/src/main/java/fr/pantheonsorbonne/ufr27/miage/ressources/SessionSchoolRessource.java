package fr.pantheonsorbonne.ufr27.miage.ressources;

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

    @Path("/SchoolRegister")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response inscrirePokemon() {

        if(sessionService.getPokemon() != null){
            SchoolSession session = sessionService.findRightSession();
            sessionService.sendSessionToMairie(session);

            if (this.sessionService.isMoneyEnough()) {
                this.sessionService.inscrirePokemon(session);
                return Response.ok().build();
            } else {
                return Response.status(422, "L'inscription n'a pas pu être effectuée.").build();
            }
        }
        else{
            return Response.status(422, "Aucun pokemon n'a été envoyé à l'école.").build();
        }
    }

}