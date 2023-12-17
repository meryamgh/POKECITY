package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.TicketDresseurAchat;
import fr.pantheonsorbonne.ufr27.miage.model.ReceiptPokemon;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.apache.camel.CamelContext;
import org.apache.camel.Handler;
import org.apache.camel.ProducerTemplate;
import jakarta.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class PurchasePokemonGateway {

    @Inject
    CamelContext camelContext;



    public void checkBankCardDresseur(TicketDresseurAchat ticketAchat) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody("direct:buyPokemon",ticketAchat);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getPokemon(){
        System.out.println("ASSEZ D4ARGNET");
    }

    public void notEnoughTogetPokemon(){
        System.out.println("PAS ASSEZ D4ARGNET");
    }
}