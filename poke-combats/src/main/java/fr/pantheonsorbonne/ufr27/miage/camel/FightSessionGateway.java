package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.FightSession;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.models.FightingSession;
import fr.pantheonsorbonne.ufr27.miage.services.FightSessionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ApplicationScoped
public class FightSessionGateway {

    @Inject
    FightSessionService fightSessionService;

    public void playBattle(List<Pokemon> pokemons, int idDresseur, Exchange exchange){

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

         FightingSession fightingSession = fightSessionService.play(PNJ, ourPokemon, idDresseur);

        Collection<Pokemon> pokemonsAfterFight = new ArrayList<>();

        exchange.getMessage().setBody(pokemonsAfterFight);
        if(fightingSession.isWinner()){
            pokemonsAfterFight.add(ourPokemon);
            exchange.getIn().setHeader("amountWin",fightingSession.getReward());

            exchange.getIn().setHeader("isWinner",fightingSession.isWinner());
        }else{
            Pokemon newPoke = new Pokemon(ourPokemon.idPokemon(),0,ourPokemon.prix(),ourPokemon.type(),ourPokemon.isAdopted(),ourPokemon.name());
             pokemonsAfterFight.add(newPoke);
             exchange.getIn().setHeader("isWinner",fightingSession.isWinner());
        }
        pokemonsAfterFight.add(PNJ);

    }

}
