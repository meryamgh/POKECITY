package fr.pantheonsorbonne.ufr27.miage.exception;

public class PokemonNotAvailable extends Exception{

    public PokemonNotAvailable(int idPokemon){
        super("Pokemon with id "+idPokemon+" is fighting for the moment come back after");
    }
}
