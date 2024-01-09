package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.gateways.FightGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.exception.DresseurBannedException;
import fr.pantheonsorbonne.ufr27.miage.exception.NotAvailablePokemonException;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class FightServiceImpl implements FightService{

    @Inject
    PokemonDao pokemonDao;

    @Inject
    DresseurService dresseurService;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.dresseurId")
    int idDresseur;

    @Inject
    FightGateway gateway;

    @Override
    public void fight(int idPokemon) throws NotAvailablePokemonException, DresseurBannedException, PokemonNotFoundException {
        Pokemon pokemon = this.pokemonDao.getPokemonById(idPokemon);
        this.dresseurService.checkAvailaibilityToPlay(idDresseur,pokemon);
        int id = pokemon.getIdPokemon();
        String name = pokemon.getName();
        int score = pokemon.getPokeScore();
        String type = pokemon.getType();
        boolean isAdopted = pokemon.getAdopted();
        fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemonDTO = new fr.pantheonsorbonne.ufr27.miage.dto.Pokemon(id, score,score, type, isAdopted, name);
        gateway.retrievePokemonFromStoreToFight(pokemonDTO);
    }
}
