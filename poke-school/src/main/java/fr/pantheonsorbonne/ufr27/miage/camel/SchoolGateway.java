package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolSession;
import fr.pantheonsorbonne.ufr27.miage.service.SchoolSessionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class SchoolGateway {

    @Inject
    CamelContext context;

    @Inject
    SchoolSessionService service;

    public void sendImprovedPokemonToMairie(Pokemon pokemon) {
        int idPokemon = pokemon.getIdPokemon();
        int pokeScore = pokemon.getPokeScore();
        fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemonToSend = new fr.pantheonsorbonne.ufr27.miage.dto.Pokemon(idPokemon, pokeScore);
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            producer.sendBody("direct:sendToMairie",pokemonToSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRightSessionToMairie(SchoolSession schoolSession) {
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            producer.sendBody("direct:sendSchoolSessionToMairie",schoolSession);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void collectingPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon){
        service.collectPokemon(pokemon);
    }


}