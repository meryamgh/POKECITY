package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Fighters;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.models.FightingSession;
import fr.pantheonsorbonne.ufr27.miage.services.FightSessionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import java.util.ArrayList;
import java.util.Collection;

@ApplicationScoped
public class FightSessionGateway {

    @Inject
    FightSessionService fightSessionService;

    public void playBattle(Fighters fighters, int idDresseur, Exchange exchange){
        Pokemon ourPokemon = fighters.ourPokemon();
        Pokemon PNJ = fighters.oponnent();
        FightingSession fightingSession = fightSessionService.play(PNJ, ourPokemon, idDresseur);
        Collection<Pokemon> pokemonsAfterFight = new ArrayList<>();
        if(fightingSession.isWinner()){
            pokemonsAfterFight.add(ourPokemon);
            exchange.getIn().setHeader("amountWin",fightingSession.getReward());
        }else{
            Pokemon newPoke = new Pokemon(ourPokemon.idPokemon(),0,ourPokemon.prix(),ourPokemon.type(),ourPokemon.isAdopted(),ourPokemon.name());
            pokemonsAfterFight.add(newPoke);
        }
        exchange.getIn().setHeader("isWinner",fightingSession.isWinner());
        pokemonsAfterFight.add(PNJ);
        exchange.getMessage().setBody(pokemonsAfterFight);
    }
}
