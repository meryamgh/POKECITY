package fr.pantheonsorbonne.ufr27.miage.camel.gateways;

import fr.pantheonsorbonne.ufr27.miage.services.BankService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Header;


@ApplicationScoped
public class BankGateway {


    @Inject
    BankService bankService;




    public void checkBalance(@Header("price") int money, @Header("idDresseur") int idDresseur, Exchange exchange) {
        boolean haveEnoughMoney = bankService.checkBalance(money, idDresseur);
        exchange.getIn().setHeader("success", haveEnoughMoney);
    }

    public void addAmountWinToBankAccount(int amount, int idDresseur){
        this.bankService.creditBankAccount(amount,idDresseur);
        System.out.println("dresseur vainqueur donc ajout d'argent");
    }


}
