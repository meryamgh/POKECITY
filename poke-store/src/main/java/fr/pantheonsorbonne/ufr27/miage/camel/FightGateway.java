package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Fighters;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.services.InventoryPokemonService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.Collection;

@ApplicationScoped
public class FightGateway {

    @Inject
    InventoryPokemonService inventoryPokemonService;

    public Collection<fr.pantheonsorbonne.ufr27.miage.dto.Pokemon> getRandomPokemonForFight(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemonDresseur, Exchange exchange) throws PokemonNotFoundException {
        Pokemon randomPokemon = this.inventoryPokemonService.getRandomPokemonFighting();
        Collection<fr.pantheonsorbonne.ufr27.miage.dto.Pokemon> pokemonFights = new ArrayList<>();
        pokemonFights.add(pokemonDresseur);
        pokemonFights.add( new fr.pantheonsorbonne.ufr27.miage.dto.Pokemon(randomPokemon.getIdPokemon(),randomPokemon.getPrix(),randomPokemon.getPrix(),randomPokemon.getType(),false, randomPokemon.getName()));
        return pokemonFights;
    }

    public void stockPokemonToStore(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon){
        this.inventoryPokemonService.addPokemonToStore(pokemon);
    }

}
