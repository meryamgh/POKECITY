package fr.pantheonsorbonne.ufr27.miage.services;

public interface SoinService {

    void checkEnoughMoney(int idPokemon, int price, int pokescore, int pricetreatment, String type);

    fr.pantheonsorbonne.ufr27.miage.dto.Pokemon soignerPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon);

    void redirectToMairie(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon);

     int getPriceTreatment(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon);
     void priseEnCharge(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon);

}
