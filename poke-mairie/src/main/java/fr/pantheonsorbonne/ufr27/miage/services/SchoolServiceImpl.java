package fr.pantheonsorbonne.ufr27.miage.services;


import fr.pantheonsorbonne.ufr27.miage.camel.gateways.SchoolGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.DresseurDao;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.exception.DresseurBannedException;
import fr.pantheonsorbonne.ufr27.miage.exception.NotAvailablePokemonException;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class SchoolServiceImpl implements SchoolService{

    @Inject
    PokemonDao pokemonDao;

    @Inject
    DresseurService dresseurService;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.dresseurId")
    int idDresseur;

    @Inject
    SchoolGateway gateway;

    @Override
    public void sendPokemonToSchool(int idPokemon) throws NotAvailablePokemonException, PokemonNotFoundException, DresseurBannedException {
        Pokemon pokemon = this.pokemonDao.getPokemonById(idPokemon);
        this.dresseurService.checkAvailaibilityToPlay(idDresseur,pokemon);
        int id = pokemon.getIdPokemon();
        int score = pokemon.getPokeScore();
        fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemonDTO = new fr.pantheonsorbonne.ufr27.miage.dto.Pokemon(id, score,score, pokemon.getType(),pokemon.getAdopted(),pokemon.getName());
        gateway.sendToSchool(pokemonDTO);
    }




}
