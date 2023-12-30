package fr.pantheonsorbonne.ufr27.miage.exception;

public class PokemonNotFoundException extends Throwable{
    public PokemonNotFoundException(int idPokemon) {
        super("Pokemon with id " + idPokemon
        + "is not available in the store");
    }

    public PokemonNotFoundException(){
        super("There is no pokemon available in the store");
    }
}
