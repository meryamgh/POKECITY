package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.services.RegistrationService;
import fr.pantheonsorbonne.ufr27.miage.services.SchoolSessionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;


@ApplicationScoped
public class SchoolGateway {

    @Inject
    RegistrationService schoolService;

    @Inject
    SchoolSessionService schoolSessionService;


    public fr.pantheonsorbonne.ufr27.miage.dto.Pokemon improvePokemon(Pokemon pokemon, int idDresseur){
        return schoolService.inscrirePokemon(pokemon, idDresseur);
    }


    public void getPriceRightSession(Pokemon pokemon, Exchange exchange){
        exchange.getIn().setHeader("price", schoolSessionService.findRightSession(pokemon.pokeScore()).getPriceSchoolSession());
    }





}