package fr.pantheonsorbonne.ufr27.miage.services;


import fr.pantheonsorbonne.ufr27.miage.camel.SchoolGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SchoolServiceImpl implements SchoolService{

    @Inject
    PokemonDao pokemonDao;

    @Inject
    SchoolGateway gateway;

    @Override
    public void sendPokemonToSchool(Pokemon pokemon){
        //Dresseur dresseur = dresseurDao.getDresseur(idDresseur);
        //if(dresseurDao.checkRightDresseur(dresseur)) {
            int id = pokemon.getIdPokemon();
            int score = pokemon.getPokeScore();
            fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon1 = new fr.pantheonsorbonne.ufr27.miage.dto.Pokemon(id, score);
            gateway.sendToSchool(pokemon1);
        //}
        //else{
            //System.out.println("le pokemon n'appartient pas au dresseur");
        //}
    }

    @Override
    public Pokemon getPokemon(int id) {
        return pokemonDao.getPokemonById(id);
    }


}
