package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.TicketDresseurAchat;
import fr.pantheonsorbonne.ufr27.miage.services.BankService;
import fr.pantheonsorbonne.ufr27.miage.services.BankServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BankGateway {

    @Inject
    BankService bank;


    public TicketDresseurAchat checkBalance(TicketDresseurAchat ticket){
        System.out.println("ticet dans gatewat "+ticket);
        BankService bank = new BankServiceImpl();
        return bank.checkBalance(ticket);
    }


}
