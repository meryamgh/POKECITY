package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.services.SoinService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;

@ApplicationScoped
public class TreatGateway {

    @Inject
    SoinService service;


    public fr.pantheonsorbonne.ufr27.miage.dto.Pokemon soigner(Pokemon pokemon, int idDresseur) {
        return service.soignerPokemon(pokemon, idDresseur);
    }

    public void getPriceTreatment(Pokemon pokemon, Exchange exchange){
        exchange.getIn().setHeader("price",this.service.getPriceTreatment(pokemon));

    }

}
