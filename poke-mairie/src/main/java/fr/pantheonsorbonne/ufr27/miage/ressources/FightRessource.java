package fr.pantheonsorbonne.ufr27.miage.ressources;


import fr.pantheonsorbonne.ufr27.miage.exception.DresseurBannedException;
import fr.pantheonsorbonne.ufr27.miage.exception.NotAvailablePokemonException;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.services.FightService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/fight")
public class FightRessource {

    @Inject
    FightService service;

    @Path("pokemon/{id}")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response fightAgainstPokemon(@PathParam("id") int idPokemon){
        try{
            this.service.fight(idPokemon);
        } catch (NotAvailablePokemonException | DresseurBannedException | PokemonNotFoundException e){
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(e.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        return Response.status(Response.Status.OK).build();
    }
}
