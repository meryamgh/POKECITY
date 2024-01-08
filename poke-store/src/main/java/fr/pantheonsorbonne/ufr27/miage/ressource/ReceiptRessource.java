package fr.pantheonsorbonne.ufr27.miage.ressource;

import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ReceiptPokemon;
import fr.pantheonsorbonne.ufr27.miage.services.ReceiptService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;

@Path("/pokestore")
public class ReceiptRessource {

    @Inject
    protected ReceiptService service;


    @Path("purchase/{idPokemon}")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response buyPokemon(@PathParam("idPokemon") int pokemon) {
        try {
            service.buyPokemon(pokemon);
        } catch (PokemonNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @Path("receipts")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<ReceiptPokemon> getAllReceiptPokemon(){
        return this.service.getAllReceipts();
    }

    @Path("receipts/{idDresseur}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<ReceiptPokemon> getAllReceiptPokemon(@PathParam("idDresseur") int idDresseur){
        return this.service.getAllReceiptsByDresseur(idDresseur);
    }
}
