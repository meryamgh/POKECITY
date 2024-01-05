package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Random;

import static java.lang.Math.abs;

@ApplicationScoped
public class WinnerServiceImpl implements WinnerService{

    @Override
    public boolean getWinner(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon oponent, Pokemon ourPokemon) {

        int chanceToWin = 50;
        int ecart = ourPokemon.pokeScore() - oponent.pokeScore();

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

        /**if(ourPokemon.pokeScore() < oponent.pokeScore()) {
            chanceToWin-=15;
        } else if (ourPokemon.pokeScore() > oponent.pokeScore()) {
            chanceToWin+=15;
        }**/

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
