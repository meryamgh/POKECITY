package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dao.TreatmentDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.TreatmentSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TestSoinServiceImpl {

    @InjectMocks
    SoinServiceImpl soinService;

    @Mock
    TreatmentDAO treatmentDAO;

    private Pokemon miago;

    @BeforeEach
    public void setup() {
        miago = new Pokemon(3, 50, 70, "feu", true, "miago");
        List<TreatmentSession> treatmentSessionList = Arrays.asList(new TreatmentSession());
        lenient().when(treatmentDAO.getAllTreatmentSessionsByDresseuer(eq(1))).thenReturn(treatmentSessionList);
    }

    @Test
    public void testSoignerPokemon() {
        Pokemon newMiago = soinService.soignerPokemon(miago, 1);
        verify(treatmentDAO, times(1)).insertTreatmentSession(eq(miago), eq(35), eq(1));

        assertEquals(70, newMiago.pokeScore());
    }

    @Test
    public void testGetAllTreamentSessionsDresseur() {
        int idDresseur = 1;
        soinService.getAllTreatmentSessionsDresseur(idDresseur);
        verify(treatmentDAO, times(1)).getAllTreatmentSessionsByDresseuer(eq(idDresseur));
    }

    @Test
    public void testGetAllTreatmentSessions() {
        soinService.getAllTreatmentSessions();
        verify(treatmentDAO, times(1)).getAllTreatmentSessions();
    }
}
