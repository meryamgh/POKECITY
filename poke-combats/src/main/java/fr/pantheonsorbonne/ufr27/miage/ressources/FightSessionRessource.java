package fr.pantheonsorbonne.ufr27.miage.ressources;

import fr.pantheonsorbonne.ufr27.miage.models.FightingSession;
import fr.pantheonsorbonne.ufr27.miage.services.FightSessionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;

@Path("/battle")
public class FightSessionRessource {

    @Inject
    protected FightSessionService service;


    @Path("pokemon")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<FightingSession> getAllSession(){
        return service.getAllFights();
    }

    @Path("pokemon/ticketSession/{idDresseur}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<FightingSession> getAllSessionDresseur(@PathParam("idDresseur")   int idDresseur){
        return service.getAllFightsDresseur(idDresseur);
    }

}
