package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.services.SoinService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
@ApplicationScoped
public class CamelRoutes extends RouteBuilder {
    @Inject
    SoinService soinService;

    @Inject
    RedirectToMairieGateway redirectToMairieGateway;

    @Inject
    SoignerPokemonGateway soignerPokemonGateway;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;


    @Override
    public void configure() throws Exception {

        from("sjms2:queue:"+jmsPrefix+"pokeInfirmerie")
                .choice()
                .when(simple("${headers.responseHaveEnoughMoney}"))
                .bean(soignerPokemonGateway, "soigner(${body})")
                .otherwise()
                .bean(redirectToMairieGateway, "redirect(${body})");
    }
}
