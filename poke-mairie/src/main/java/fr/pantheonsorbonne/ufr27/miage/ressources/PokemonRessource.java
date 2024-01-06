package fr.pantheonsorbonne.ufr27.miage.ressources;

import fr.pantheonsorbonne.ufr27.miage.exception.NotAvailablePokemonException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.services.PokemonService;
import fr.pantheonsorbonne.ufr27.miage.services.SchoolService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;

@Path("/pokemairie")
public class PokemonRessource {

    @Inject
    SchoolService schoolService;

    @Inject
    PokemonService pokemonService;

    @Path("/pokemon/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Pokemon getPokemonById(@PathParam("id") int id) {
        return this.schoolService.getPokemon(id);
    }

    @Path("/goToSchool/{idPokemon}")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response goToSchool(
            @PathParam("idPokemon") int idPokemon
        ){
        try{
            schoolService.sendPokemonToSchool(idPokemon);
        } catch (NotAvailablePokemonException e){
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(e.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.status(Response.Status.OK).build();
    }

    @Path("/pokemon/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Pokemon> getAllPokemonFromStore() {
        return this.pokemonService.getAllPokemon();
    }


}
