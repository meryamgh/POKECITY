//package fr.pantheonsorbonne.ufr27.miage.services;
//
//import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
//import fr.pantheonsorbonne.ufr27.miage.dto.PokemonType;
//import jakarta.enterprise.context.ApplicationScoped;
//import java.util.Random;
//
//
//@ApplicationScoped
//public class GameServiceImpl implements GameService {
//
//    @Override
//    public boolean getWinner(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon oponent, Pokemon ourPokemon) {
//
//        int chanceToWin = 50;
//        int ecart = ourPokemon.pokeScore() - oponent.pokeScore();
//
//        switch (ourPokemon.type()) {
//
//            case "eau":
//                if(oponent.type().equals(PokemonType.FEU.toString())) {
//                    chanceToWin+=10;
//                } else if (oponent.type().equals(PokemonType.PLANTE.toString())){
//                    chanceToWin-=10;
//                }
//                break;
//
//            case "feu":
//                if(oponent.type().equals(PokemonType.PLANTE.toString())) {
//                    chanceToWin+=10;
//                } else if (oponent.type().equals(PokemonType.EAU.toString())){
//                    chanceToWin-=10;
//                }
//                break;
//
//            case "plante":
//                if(oponent.type().equals(PokemonType.EAU.toString())) {
//                    chanceToWin+=10;
//                } else if (oponent.type().equals(PokemonType.FEU.toString())){
//                    chanceToWin-=10;
//                }
//                break;
//        }
//
//        if(ecart < 0) {
//            if(ecart >= -10) {
//                chanceToWin-=5;
//            } else if(ecart >= -50) {
//                chanceToWin-=10;
//            } else if (ecart >= -100) {
//                chanceToWin-=15;
//            } else {
//                chanceToWin-=20;
//            }
//        }
//
//        if(ecart > 0) {
//            if(ecart <= 10) {
//                chanceToWin+=5;
//            } else if(ecart <= 50) {
//                chanceToWin+=10;
//            } else if (ecart <= 100) {
//                chanceToWin+=15;
//            } else {
//                chanceToWin+=20;
//            }
//        }
//
//        return percentGiven(chanceToWin);
//    }
//
//    @Override
//    public boolean percentGiven(int percent)
//    {
//        Random rand = new Random();
//        int number = rand.nextInt(100);
//        return number < percent;
//    }
//}


package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.dto.PokemonType;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class GameServiceImpl implements GameService {

    @Override
    public boolean getWinner(Pokemon opponent, Pokemon ourPokemon) {
        int chanceToWin = 50;
        int ecart = ourPokemon.pokeScore() - opponent.pokeScore();

        PokemonType ourPokemonType = PokemonType.valueOf(ourPokemon.type().toUpperCase());
        PokemonType opponentType = PokemonType.valueOf(opponent.type());

        chanceToWin += calculateTypeAdvantage(ourPokemonType, opponentType);
        chanceToWin += calculateScoreAdvantage(ecart);

        return percentGiven(chanceToWin);
    }

    @Override
    public int calculateTypeAdvantage(PokemonType ourType, PokemonType opponentType) {
        if (ourType == null || opponentType == null) {
            return 0;
        }

        int typeAdvantage = 0;

        if (ourType.isStrongAgainst(opponentType)) {
            typeAdvantage += 10;
        } else if (ourType.isWeakAgainst(opponentType)) {
            typeAdvantage -= 10;
        }

        return typeAdvantage;
    }

    @Override
    public int calculateScoreAdvantage(int ecart) {
        int scoreAdvantage = 0;

        if (ecart < 0) {
            scoreAdvantage -= calculateScorePenalty(ecart);
        } else if (ecart > 0) {
            scoreAdvantage += calculateScoreBonus(ecart);
        }

        return scoreAdvantage;
    }

    @Override
    public int calculateScorePenalty(int ecart) {
        if (ecart >= -100) {
            return Math.min(-20, -5 * (ecart / 10));
        } else {
            return -20;
        }
    }

    @Override
    public int calculateScoreBonus(int ecart) {
        if (ecart <= 100) {
            return Math.min(20, 5 * (ecart / 10));
        } else {
            return 20;
        }
    }

    @Override
    public boolean percentGiven(int percent) {
        Random rand = new Random();
        int number = rand.nextInt(100);
        return number < percent;
    }
}
