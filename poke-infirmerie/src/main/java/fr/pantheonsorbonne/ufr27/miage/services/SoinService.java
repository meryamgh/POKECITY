package fr.pantheonsorbonne.ufr27.miage.services;

public interface SoinService {

    public void checkEnoughMoney(int idPokemon, int price, int pokescore, int pricetreatment);

    public void soignerPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon);

    public void redirectToMairie(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon);

    public int getPriceTreatment(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon);

    public void priseEnCharge(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon);

}
