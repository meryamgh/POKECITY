package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.SchoolGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.DresseurDao;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class SchoolServiceImpl implements SchoolService{

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.dresseurId")
    Integer idDresseur;

    @Inject
    SchoolGateway gateway;

    @Inject
    DresseurDao dresseurDao;

    @Override
    public void sendPokemonToSchool(Pokemon pokemon){
        if(dresseurDao.checkRightDresseur(idDresseur)) {
            int id = pokemon.getIdPokemon();
            int score = pokemon.getPokeScore();
            fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon1 = new fr.pantheonsorbonne.ufr27.miage.dto.Pokemon(id, score);
            gateway.sendToSchool(pokemon1);
        }
        else{
            System.out.println("le pokemon n'appartient pas au dresseur");
        }
    }
}
