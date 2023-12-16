package fr.pantheonsorbonne.ufr27.miage.ressource;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.services.PurchasePokemonService;
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

    @Inject
    protected PurchasePokemonService buyPokemonService;

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

    @Path("purchase")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response isTicketValid() {
        buyPokemonService.buyPokemon(new fr.pantheonsorbonne.ufr27.miage.dto.Pokemon(1, "ghulamo", 100,false));
        return Response.status(422, "invalid verification code").build();

    }

}
