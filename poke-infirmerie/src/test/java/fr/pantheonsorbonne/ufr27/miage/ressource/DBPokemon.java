package fr.pantheonsorbonne.ufr27.miage.ressource;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.TreatmentSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class DBPokemon {

    @Inject
    EntityManager em;

    @Transactional
    public void truncateAllTables() {

        em.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();

        List<String> tableNames = em.createNativeQuery(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES " +
                        "WHERE TABLE_SCHEMA='PUBLIC'").getResultList();

        for (String tableName : tableNames) {
            em.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
        }

        em.createNativeQuery("SET FOREIGN_KEY_CHECKS=1;").executeUpdate();
    }
    @Transactional
    public void truncateTreatmentSession() {
        em.createNativeQuery("TRUNCATE TABLE TreatmentSession").executeUpdate();
    }
    @Transactional
    public TestData createData() {
        List<Pokemon> listePokemon = new ArrayList<>();
        listePokemon.add(new Pokemon(1, 50, 50, "plante", true, "miago"));
        TreatmentSession treatmentSession = new TreatmentSession();
        treatmentSession.setIdPokemon(4);
        treatmentSession.setPriceTreatment(50);
        treatmentSession.setTreatmentDate(new Date());
        treatmentSession.setTimeTreatment(2);
        em.persist(treatmentSession);

        return new TestData(treatmentSession);
    }
}
