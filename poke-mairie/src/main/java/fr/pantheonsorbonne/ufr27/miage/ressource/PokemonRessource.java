package fr.pantheonsorbonne.ufr27.miage.ressource;


import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.services.PokemonService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;

@Path("/pokemairie")
public class PokemonRessource {

    @Inject
    PokemonService pokemonService;

    @Path("/pokemon/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Pokemon getPokemonById(@PathParam("id") int id) throws PokemonNotFoundException {
        return this.pokemonService.getPokemon(id);
    }

    @Path("/pokemon/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Pokemon> getAllPokemonFromStore() {
        return this.pokemonService.getAllPokemon();
    }

    @Path("pokemon/localisation/{localisation}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Pokemon> getPokemonByLocalisation(@PathParam("localisation") String localisation) {
        return this.pokemonService.getPokemonByLocalisation(localisation);
    }


}
