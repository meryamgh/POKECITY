package fr.pantheonsorbonne.ufr27.miage.ressource;

import fr.pantheonsorbonne.ufr27.miage.model.TreatmentSession;
import io.restassured.response.Response;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class TestInfirmerieRessource {

    @Inject
    DBPokemon pokemon;

    TestData testData;

    @Inject
    EntityManager em;

    @BeforeEach
    @Transactional
    public void setup() {
        pokemon.truncateAllTables();
        testData = pokemon.createData();
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

        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        TreatmentSession[] listResponse = response.as(TreatmentSession[].class);
         assertEquals(1, listResponse.length);
    }

    @Test
    public void testGetAllTreatmentsByIdDresseur() {
    Response response = given()
     .when()
     .get("infirmerie/treatments/" + testData.treatmentSession().getIdDresseur())
     .then()
     .statusCode(200)
     .extract()
     .response();

    TreatmentSession[] listResponse = response.as(TreatmentSession[].class);
    assertEquals(1, listResponse.length);
    }

}

