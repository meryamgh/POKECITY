package fr.pantheonsorbonne.ufr27.miage.services;

public interface RegistrationService {
    fr.pantheonsorbonne.ufr27.miage.dto.Pokemon inscrirePokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon,  int idDresseur);

    fr.pantheonsorbonne.ufr27.miage.dto.Pokemon upgradeScore(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon,int gain);



}