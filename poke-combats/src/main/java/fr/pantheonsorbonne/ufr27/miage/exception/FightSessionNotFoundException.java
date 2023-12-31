package fr.pantheonsorbonne.ufr27.miage.exception;

public class FightSessionNotFoundException extends Exception{

    public FightSessionNotFoundException(){
        super("There is no fightSession done for the moment");
    }

}
