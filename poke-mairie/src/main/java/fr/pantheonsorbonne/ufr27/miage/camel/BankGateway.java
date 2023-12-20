package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.dto.TicketDresseurAchat;
import fr.pantheonsorbonne.ufr27.miage.services.BankService;
import fr.pantheonsorbonne.ufr27.miage.services.DresseurService;
import fr.pantheonsorbonne.ufr27.miage.services.PokemonService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class BankGateway {


    @Inject
    BankService bankService;


    @Inject
    CamelContext camelContext;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    DresseurService dresseurService;

    @Inject
    PokemonService pokemonService;


    public void checkBalance(Pokemon pokemon, int idDresseur){
        System.out.println("ticet dans gatewat "+pokemon);
        if(bankService.checkBalance(pokemon.pokeScore(), idDresseur)){
            this.dresseurService.affectPokemonToDresseur(pokemon.idPokemon(), idDresseur);
        }
    }


    


}
