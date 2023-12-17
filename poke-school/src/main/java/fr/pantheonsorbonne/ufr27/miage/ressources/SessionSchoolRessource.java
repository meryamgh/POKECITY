package fr.pantheonsorbonne.ufr27.miage.ressources;


import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;

@Path("/pokeschool")
public class SessionSchoolRessource {

    @Path("session")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Pokemon> getAllStoredPokemons() {
        return null;
    }
}
