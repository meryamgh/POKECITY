package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.services.PokemonService;
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
    PokemonService pokeService;

    public void improvePokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon){
        pokeService.updatePokemon(pokemon);
    }


    public void sendToSchool(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon){
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            producer.sendBody("direct:sendPokemonToSchool",pokemon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
