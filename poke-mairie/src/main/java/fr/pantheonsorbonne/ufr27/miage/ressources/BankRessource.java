package fr.pantheonsorbonne.ufr27.miage.ressources;

import fr.pantheonsorbonne.ufr27.miage.model.BankAccount;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.services.BankService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;

@Path("/bank")
public class BankRessource {

    @Inject
    BankService service;
    @Path("bank/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public BankAccount getDresseurBankaccount (@PathParam("id") int idDresseur){
        System.out.println("okok");
        return this.service.getCardBank(idDresseur);
    }
}
