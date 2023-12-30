package fr.pantheonsorbonne.ufr27.miage.ressources;

import fr.pantheonsorbonne.ufr27.miage.model.SchoolTicket;
import fr.pantheonsorbonne.ufr27.miage.service.TicketService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;

@Path("/pokeschool/Tickets")
public class SchoolTicketRessource {

    @Inject
    TicketService ticketService;

    @Path("/AllTickets")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<SchoolTicket> getAllSchoolTickets() {
        return this.ticketService.getAllTickets();
    }
}
