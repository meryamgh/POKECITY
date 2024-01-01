package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.FightSession;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
public class FightSessionGateway {


    public FightSession playBattle(Collection<Pokemon> pokemons, int idDresseur){
        Optional<Pokemon> ourPokemon = pokemons.stream()
                .filter(Pokemon::isAdopted)
                .findFirst();

        if (ourPokemon.isPresent()) {
            Pokemon pokemonDresseur = ourPokemon.get();

            Optional<Pokemon> otherPokemon = pokemons.stream()
                    .filter(pokemon -> !pokemon.isAdopted())
                    .findFirst();

            if (otherPokemon.isPresent()) {
                Pokemon pokemonAdversaire = otherPokemon.get();
                return new FightSession(idDresseur,pokemonAdversaire,pokemonDresseur,true,500);
            }
        }
        return null;

    }

}
