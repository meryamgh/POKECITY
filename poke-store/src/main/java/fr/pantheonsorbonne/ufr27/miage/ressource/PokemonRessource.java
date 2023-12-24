package fr.pantheonsorbonne.ufr27.miage.ressource;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.services.InventoryPokemonService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;

@Path("/pokestore/inventory")
public class PokemonRessource {

    @Inject
    protected InventoryPokemonService service;


    @Path("pokemon")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Pokemon> getAllStoredPokemons() {
        return service.getAllPokemon();
    }

    @Path("pokemon/{price}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Pokemon> getSotredPokemonsByPriceLimit(@PathParam("price") int price) {
        return service.getPokemonByPrice(price);
    }

}