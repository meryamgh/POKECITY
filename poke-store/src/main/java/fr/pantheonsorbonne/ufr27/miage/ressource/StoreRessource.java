package fr.pantheonsorbonne.ufr27.miage.ressource;

import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.ReceiptPokemon;
import fr.pantheonsorbonne.ufr27.miage.services.StoreService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;

@Path("/pokestore")
public class StoreRessource {

    @Inject
    protected StoreService service;


    @Path("pokemon")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Pokemon> getAllStoredPokemons() {
        return service.getAllPokemon();
    }

    @Path("pokemon/{price}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Pokemon> getSotredPokemonsByPriceLimit(@PathParam("price") int price){
        return service.getPokemonByPrice(price);
    }

    @Path("purchase/{idPokemon}")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response buyPokemon(@PathParam("idPokemon") int pokemon) throws PokemonNotFoundException {
        try {
            service.buyPokemon(pokemon);
        } catch (PokemonNotFoundException e){
            String errorMessage = "Le Pokémon avec l'ID " + pokemon + " n'existe pas dans le PokéStore.";
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @Path("receipts")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<ReceiptPokemon> getAllReceiptPokemon(){
        return this.service.getAllReceipts();
    }

}
