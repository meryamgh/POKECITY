package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.services.DresseurService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class DresseurGateway {
    @Inject
    DresseurService dresseurService;

    public void bannedDresseur(int idDresseur){
        this.dresseurService.changeBannedStatus(idDresseur);
    }

    public void deletePokemonFromDresseurPokedex(Pokemon pokemon, int idDresseur){
        this.dresseurService.deletePokemonDresseur(pokemon,idDresseur);
    }

}