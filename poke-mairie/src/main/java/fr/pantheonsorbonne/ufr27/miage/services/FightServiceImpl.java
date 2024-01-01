package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.camel.StoreGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.DresseurDao;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.exception.NotAvailablePokemonException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class FightServiceImpl implements FightService{

    @Inject
    PokemonDao pokemonDao;

    @Inject
    DresseurDao dresseurDao;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.dresseurId")
    int idDresseur;

    @Inject
    StoreGateway gateway;

    @Override
    public void fight(int idPokemon) throws NotAvailablePokemonException {
        this.dresseurDao.isDresseurPokemon(idDresseur,idPokemon);
        Pokemon pokemon = this.pokemonDao.getPokemonById(idPokemon);
        if(!pokemon.getLocalisation().equals("mairie")){
            throw new NotAvailablePokemonException("Pokemon with ID "+idPokemon+" is not available because is in the "+pokemon.getLocalisation());
        }
        int id = pokemon.getIdPokemon();
        int score = pokemon.getPokeScore();
        fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemonDTO = new fr.pantheonsorbonne.ufr27.miage.dto.Pokemon(id, score,score, pokemon.getType(),pokemon.getAdopted(),pokemon.getName());
        gateway.retrievePokemonFromStoreToFight(pokemonDTO);
    }
}
