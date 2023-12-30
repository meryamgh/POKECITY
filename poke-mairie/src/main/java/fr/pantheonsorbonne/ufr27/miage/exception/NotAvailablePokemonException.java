package fr.pantheonsorbonne.ufr27.miage.exception;

import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;

public class NotAvailablePokemonException extends Exception{

    public NotAvailablePokemonException(String message){
        super(message);
    }

}
