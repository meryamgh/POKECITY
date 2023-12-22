package fr.pantheonsorbonne.ufr27.miage.services;

public interface SoinService {

    public void enoughMoney(int idDresseur, int priceTreatment);

    public void soignerPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon);

    public void redirectToMairie(int idPokemon);

    public int getPriceTreatment(int idPokemon);

}
