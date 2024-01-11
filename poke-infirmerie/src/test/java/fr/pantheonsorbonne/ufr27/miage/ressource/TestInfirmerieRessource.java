package fr.pantheonsorbonne.ufr27.miage.ressource;

import io.restassured.response.Response;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class TestInfirmerieRessource {

    @BeforeEach
    @Transactional
    public void setup() {
    }

    @Test
    public void testInfirmerieRessource() {
        Response response = given()
                .when()
                .get("infirmerie/treatments")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    /**@Path("/infirmerie")
    public class InfirmerieRessource {

        @Inject
        protected SoinService service;

        @Path("treatments")
        @GET
        @Produces({MediaType.APPLICATION_JSON})
        public Collection<TreatmentSession> getAllTreatments() {
            return service.getAllTreatmentSessions();
        }**/

    /**@Path("{idVendor}/venues")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Gig> getGigs(@PathParam("idVendor") int idVendor) {
        Collection<Gig> gig = service.getAvailableGigs(idVendor);
        if (gig.isEmpty()) {
            throw new WebApplicationException(404);
        } else {
            return gig;
        }
    }

    @Test

    public void testGig() {
        Response response = given()
                .when()
                .get("vendor/" + testData.vendor().getId() + "/venues")
                .then()
                .statusCode(200)
                .extract()
                .response();

        Gig[] gigs = response.as(Gig[].class);
        assertEquals(1, gigs.length);
        assertEquals("radiohead", gigs[0].getArtistName());
        assertEquals("Le Zenith", gigs[0].getLocation());

        given()
                .when()
                .get("vendor/" + 999999 + "/venues")
                .then()
                .statusCode(404);

    }**/
}
