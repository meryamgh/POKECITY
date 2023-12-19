package fr.pantheonsorbonne.ufr27.miage.ressources;


import fr.pantheonsorbonne.ufr27.miage.dto.TicketDresseurAchat;
import fr.pantheonsorbonne.ufr27.miage.services.BankService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/pokemairie")
public class TestRessource {

    @Inject
    BankService service;

    @Path("pokemon")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getSotredPokemonsByPriceLimit(){
        System.out.println("okok");
        service.checkBalance(100,1);
        return null; //TODO replace this stub to something useful
    }
}
