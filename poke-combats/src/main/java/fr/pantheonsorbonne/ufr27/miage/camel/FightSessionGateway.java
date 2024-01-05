package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.FightSession;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.models.FightingSession;
import fr.pantheonsorbonne.ufr27.miage.services.FightSessionService;
import fr.pantheonsorbonne.ufr27.miage.services.FightSessionServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ApplicationScoped
public class FightSessionGateway {

    @Inject
    FightSessionService fightSessionService;


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
        FightingSession fightingSession = fightSessionService.play(PNJ, ourPokemon, idDresseur);
        return new FightSession(idDresseur, PNJ, ourPokemon, fightingSession.isWinner(), fightingSession.getReward());
    }

}
