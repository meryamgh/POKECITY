package fr.pantheonsorbonne.ufr27.miage.ressources;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.services.BankService;
import fr.pantheonsorbonne.ufr27.miage.services.DresseurService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;

@Path("/pokemairie")
public class DresseurRessource {

    @Inject
    DresseurService service;

    @Path("pokemon/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Pokemon> getSotredPokemonsByPriceLimit(@PathParam("id") int idDresseur){
        System.out.println("okok");
        return this.service.getAllDresseurPokemon(idDresseur);
    }

    @Path("bank/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Pokemon> getDresseurBankaccount (@PathParam("id") int idDresseur){
        System.out.println("okok");
        return this.service.getAllDresseurPokemon(idDresseur);
    }
}
