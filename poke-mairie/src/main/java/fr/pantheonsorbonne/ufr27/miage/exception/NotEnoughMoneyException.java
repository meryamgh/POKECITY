package fr.pantheonsorbonne.ufr27.miage.exception;

public class NotEnoughMoneyException extends Exception{

    public NotEnoughMoneyException(int amount) {
        super("Dresseus has less than " + amount
                + " Payment is rejected");
    }

}
