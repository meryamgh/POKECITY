package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.services.DresseurService;
import fr.pantheonsorbonne.ufr27.miage.services.PokemonService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class PokemonGateway {


    @Inject
    PokemonService pokeService;

    @Inject
    DresseurService dresseurService;




    public void affectPokemonToDresseur(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon, int idDresseur) {
        System.out.println("ticet dans gatewat "+pokemon);
        this.dresseurService.affectPokemonToDresseur(pokemon.idPokemon(), idDresseur);

    }

    public void improvePokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon){
        pokeService.updatePokemon(pokemon);
    }

    public void setLocalisationPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon, String newLocalisation){
        this.pokeService.updatePokemonLocalisation(pokemon.idPokemon(), newLocalisation);
    }

}
