package fr.pantheonsorbonne.ufr27.miage.ressources;

import fr.pantheonsorbonne.ufr27.miage.camel.RedirectToMairieGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.SoignerPokemonGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.TreatmentDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.services.SoinServiceImpl;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestSoinServiceImpl {

    @InjectMocks
    SoinServiceImpl soinService;

    @Mock
    TreatmentDAO treatmentDAO;

    @Mock
    SoignerPokemonGateway pokemonGateway;

    @Mock
    RedirectToMairieGateway redirectToMairieGateway;

    @BeforeEach
    public void setup() {
        Pokemon pika = new Pokemon(3, 90, 100);
        int priceTreatment = 50;
    }

    @Test
    public void testEnoughMoney() {

    }
}
