package fr.pantheonsorbonne.ufr27.miage.ressources;


import fr.pantheonsorbonne.ufr27.miage.exception.DresseurBannedException;
import fr.pantheonsorbonne.ufr27.miage.exception.NotAvailablePokemonException;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.services.SchoolService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pokemairie")
public class SchoolRessource {

    @Inject
    SchoolService schoolService;


    @Path("/goToSchool/{idPokemon}")
    @POST
    @Produces({ MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response goToSchool(
            @PathParam("idPokemon") int idPokemon
    ){
        try{
            schoolService.sendPokemonToSchool(idPokemon);
        } catch (NotAvailablePokemonException | PokemonNotFoundException | DresseurBannedException e){
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(e.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.status(Response.Status.OK).build();
    }

}
