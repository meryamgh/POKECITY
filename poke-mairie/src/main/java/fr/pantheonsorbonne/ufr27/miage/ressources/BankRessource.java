package fr.pantheonsorbonne.ufr27.miage.ressources;

import fr.pantheonsorbonne.ufr27.miage.model.BankAccount;
import fr.pantheonsorbonne.ufr27.miage.services.BankService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/bank")
public class BankRessource {

    @Inject
    BankService service;
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public BankAccount getDresseurBankaccount (@PathParam("id") int idDresseur){
        return this.service.getCardBank(idDresseur);
    }
}
