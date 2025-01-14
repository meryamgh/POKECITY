package fr.pantheonsorbonne.ufr27.miage.camel.gateways;


import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.services.DresseurService;
import fr.pantheonsorbonne.ufr27.miage.services.PokemonService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;


@ApplicationScoped
public class PokemonGateway {


    @Inject
    PokemonService pokeService;

    @Inject
    DresseurService dresseurService;




    public void affectPokemonToDresseur(Pokemon pokemon, int idDresseur) throws PokemonNotFoundException {
        this.dresseurService.affectPokemonToDresseur(pokemon.idPokemon(), idDresseur);

    }

    public void improvePokemon(Pokemon pokemon) throws PokemonNotFoundException {
        pokeService.updatePokemon(pokemon);
    }

    public void setLocalisationPokemon(Pokemon pokemon, String newLocalisation){
        this.pokeService.updatePokemonLocalisation(pokemon.idPokemon(), newLocalisation);
    }

    public void isDresseurOutOfPokemons(int idDresseur, Exchange exchange){
        exchange.getIn().setHeader("isLastPokemon",this.dresseurService.isDresseurOutOfPokemons(idDresseur));
    }

    public void addNewPokemonFromStore(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon newPokemon){
        this.pokeService.addNewPokemon(newPokemon);
    }



}
