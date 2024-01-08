package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.dto.PokemonType;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Random;


@ApplicationScoped
public class GameServiceImpl implements GameService {

    @Override
    public boolean getWinner(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon oponent, Pokemon ourPokemon) {

        int chanceToWin = 50;
        int ecart = ourPokemon.pokeScore() - oponent.pokeScore();

        switch (ourPokemon.type()) {

            case "eau":
                if(oponent.type().equals(PokemonType.FEU.toString())) {
                    chanceToWin+=10;
                } else if (oponent.type().equals(PokemonType.PLANTE.toString())){
                    chanceToWin-=10;
                }
                break;

            case "feu":
                if(oponent.type().equals(PokemonType.PLANTE.toString())) {
                    chanceToWin+=10;
                } else if (oponent.type().equals(PokemonType.EAU.toString())){
                    chanceToWin-=10;
                }
                break;

            case "plante":
                if(oponent.type().equals(PokemonType.EAU.toString())) {
                    chanceToWin+=10;
                } else if (oponent.type().equals(PokemonType.FEU.toString())){
                    chanceToWin-=10;
                }
                break;
        }

        if(ecart < 0) {
            if(ecart >= -10) {
                chanceToWin-=5;
            } else if(ecart >= -50) {
                chanceToWin-=10;
            } else if (ecart >= -100) {
                chanceToWin-=15;
            } else {
                chanceToWin-=20;
            }
        }

        if(ecart > 0) {
            if(ecart <= 10) {
                chanceToWin+=5;
            } else if(ecart <= 50) {
                chanceToWin+=10;
            } else if (ecart <= 100) {
                chanceToWin+=15;
            } else {
                chanceToWin+=20;
            }
        }

        return percentGiven(chanceToWin);
    }

    @Override
    public boolean percentGiven(int percent)
    {
        Random rand = new Random();
        int number = rand.nextInt(100);
        return number < percent;
    }
}
