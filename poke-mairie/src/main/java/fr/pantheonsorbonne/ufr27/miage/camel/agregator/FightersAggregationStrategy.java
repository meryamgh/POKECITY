package fr.pantheonsorbonne.ufr27.miage.camel.agregator;

import fr.pantheonsorbonne.ufr27.miage.dto.Fighters;
import fr.pantheonsorbonne.ufr27.miage.dto.Pokemon;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;



public class FightersAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }
        Pokemon oldBody = oldExchange.getIn().getBody(Pokemon.class);
        Pokemon newBody = newExchange.getIn().getBody(Pokemon.class);
        if(oldBody.isAdopted()){
            oldExchange.getIn().setBody(new Fighters(newBody,oldBody));
        }else{
            oldExchange.getIn().setBody(new Fighters(oldBody,newBody));
        }
        return oldExchange;
    }
}