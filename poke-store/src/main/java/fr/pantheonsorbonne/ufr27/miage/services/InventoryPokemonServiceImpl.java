package fr.pantheonsorbonne.ufr27.miage.services;

import com.github.javafaker.Faker;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonStockDao;
import fr.pantheonsorbonne.ufr27.miage.dto.PokemonType;
import fr.pantheonsorbonne.ufr27.miage.exception.PokemonNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;
import java.util.Random;

@ApplicationScoped
public class InventoryPokemonServiceImpl implements InventoryPokemonService{
    @Inject
    PokemonStockDao stockDao;

    private static final Faker faker = new Faker();

    private static final Random random = new Random();

    private static int idGenerate= 100;

    @Override
    public Collection<Pokemon> getAllPokemon() {
        return stockDao.getStock();
    }

    @Override
    public Collection<Pokemon> getPokemonByPrice(int price){
        return stockDao.getStockPokemonByPrice(price);
    }

    @Override
    public void deletePokemon(int idPokemon) throws PokemonNotFoundException  {
        this.stockDao.deletePokemon(idPokemon);
    }

    @Override
    public Pokemon getRandomPokemonFighting() throws PokemonNotFoundException {
        Pokemon pokemonFind = this.stockDao.getRandomPokemon();
        this.stockDao.deletePokemon(pokemonFind.getIdPokemon());
        return pokemonFind;
    }

    @Override
    public void addPokemonToStore(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon) {
        this.stockDao.addPokemonToStore(pokemon);
    }

    public static PokemonType generateRandomPokemonType() {
        PokemonType[] allTypes = PokemonType.values();
        int randomIndex = random.nextInt(allTypes.length);
        return allTypes[randomIndex];
    }

    @Override
    public fr.pantheonsorbonne.ufr27.miage.dto.Pokemon addNewPokemonToStore() {
        String name = faker.pokemon().name();
        int randomNumber = random.nextInt(100 - 20 + 1) + 20;
        String type = generateRandomPokemonType().toString();
        fr.pantheonsorbonne.ufr27.miage.dto.Pokemon p = new fr.pantheonsorbonne.ufr27.miage.dto.Pokemon(idGenerate++,randomNumber,randomNumber,type,false,name);
        this.stockDao.addPokemonToStore(p);
        return p;
    }


}
