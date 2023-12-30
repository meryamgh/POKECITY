package fr.pantheonsorbonne.ufr27.miage.ressources;

import fr.pantheonsorbonne.ufr27.miage.services.BankService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pokemairie")
public class TestRessource {

    @Inject
    BankService service;

    @Path("pokemon")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSotredPokemonsByPriceLimit() {

            service.checkBalance(100,1);
         /**catch (NotEnoughMoneyException e){
            String errorMessage = "Pas Assez d'argent !!!";
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage)
                    .type(MediaType.TEXT_PLAIN)
                    .build();**/
        return Response.status(Response.Status.OK).build();

    }
}
