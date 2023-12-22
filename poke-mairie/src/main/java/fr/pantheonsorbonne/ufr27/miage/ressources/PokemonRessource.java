package fr.pantheonsorbonne.ufr27.miage.ressources;

import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/pokemairie")
public class PokemonRessource {

    @Inject
    PokemonDao pokemonDao;

    @Path("/pokemon/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Pokemon getPokemonById(@PathParam("id") int id) {
        return this.pokemonDao.getPokemonById(id);
    }

    /*
    @Path("/goToSchool/{idPokemon}")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response goToSchool(
            @PathParam("idPokemon") int idPokemon
        ) {

        Pokemon pokemon = getPokemonById(idPokemon);
        schoolService.sendPokemonToSchool(pokemon);

        return Response.ok().build();

    }

     */
}
