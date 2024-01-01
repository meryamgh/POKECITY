package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Random;

@ApplicationScoped
public class WinnerServiceImpl implements WinnerService{

    @Override
    public boolean getWinner(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon oponent, Pokemon ourPokemon) {

        int chanceToWin = 50;

        switch (ourPokemon.type()) {

            case "eau":
                if(oponent.type() == "feu") {
                    chanceToWin+=10;
                } else if (oponent.type() == "plante"){
                    chanceToWin-=10;
                }
                break;

            case "feu":
                if(oponent.type() == "plante") {
                    chanceToWin+=10;
                } else if (oponent.type() == "eau"){
                    chanceToWin-=10;
                }
                break;

            case "plante":
                if(oponent.type() == "eau") {
                    chanceToWin+=10;
                } else if (oponent.type().equals("feu")){
                    chanceToWin-=10;
                }
                break;
        }

        if(ourPokemon.pokeScore() < oponent.pokeScore()) {
            chanceToWin-=15;
        } else if (ourPokemon.pokeScore() > oponent.pokeScore()) {
            chanceToWin+=15;
        }

        return percentGiven(chanceToWin);
    }

    @Override
    public boolean percentGiven(int percent)
    {
        Random rand = new Random();
        int number = rand.nextInt(100);
        if(number < percent)
            return true;
        else
            return false;
    }
}
