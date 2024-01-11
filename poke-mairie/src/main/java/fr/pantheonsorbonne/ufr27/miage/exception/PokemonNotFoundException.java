package fr.pantheonsorbonne.ufr27.miage.exception;

public class PokemonNotFoundException extends Exception{

    public PokemonNotFoundException(int id) {
        super("Pokemon with id :  " + id
                + " does not exist");
    }

}
