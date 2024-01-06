package fr.pantheonsorbonne.ufr27.miage.ressources;

import fr.pantheonsorbonne.ufr27.miage.exception.FightSessionNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.models.FightingSession;
import fr.pantheonsorbonne.ufr27.miage.services.FightSessionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;

@Path("/battle")
public class FightSessionRessource {

    @Inject
    protected FightSessionService service;


    @Path("pokemon")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<FightingSession> getAllStoredPokemons() throws FightSessionNotFoundException {
//        Collection<FightingSession> sessions =  service.getAllFights();
//        if (sessions.isEmpty()) {
//            throw new FightSessionNotFoundException();
//        } else {
//            return sessions;
//        }
        return service.getAllFights();
    }
}
