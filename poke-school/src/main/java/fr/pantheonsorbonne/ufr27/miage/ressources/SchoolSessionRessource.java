package fr.pantheonsorbonne.ufr27.miage.ressources;

import fr.pantheonsorbonne.ufr27.miage.model.SchoolSession;
import fr.pantheonsorbonne.ufr27.miage.service.SchoolSessionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;

@Path("/pokeschool/Sessions")
public class SchoolSessionRessource {

    @Inject
    SchoolSessionService schoolSessionService;

    @Path("/AllSessions")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<SchoolSession> getAllSchoolSessions() {
        return this.schoolSessionService.getAllSessions();
    }

}
