package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.service.RegistrationService;
import fr.pantheonsorbonne.ufr27.miage.service.SchoolSessionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class SchoolGateway {

    @Inject
    CamelContext context;

    @Inject
    RegistrationService schoolService;

    @Inject
    SchoolSessionService schoolSessionService;

    public fr.pantheonsorbonne.ufr27.miage.dto.Pokemon improvePokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon){
        return schoolService.inscrirePokemon(pokemon);
    }

    public void getPriceRightSession(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon, Exchange exchange){
        exchange.getIn().setHeader("price", schoolSessionService.findRightSession(pokemon.pokeScore()).getPriceSchoolSession());
    }

}