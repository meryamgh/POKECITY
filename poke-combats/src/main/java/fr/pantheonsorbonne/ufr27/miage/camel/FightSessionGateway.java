package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.FightSession;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FightSessionGateway {


    public FightSession playBattle(List<Pokemon> pokemons, int idDresseur){
        Pokemon ourPokemon;
        Pokemon PNJ;
        if(pokemons.get(0).isAdopted()){
            ourPokemon = pokemons.get(0);
            PNJ = pokemons.get(1);
        }
        else{
            ourPokemon = pokemons.get(1);
            PNJ = pokemons.get(0);
        }

        Pokemon newPoke = new Pokemon(ourPokemon.idPokemon(),0,ourPokemon.prix(),ourPokemon.type(),ourPokemon.isAdopted(),ourPokemon.name());
        return new FightSession(idDresseur, PNJ, newPoke, false, 300);

    }

}
