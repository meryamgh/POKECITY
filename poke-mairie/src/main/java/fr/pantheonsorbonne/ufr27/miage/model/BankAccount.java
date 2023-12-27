package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBankAccount", nullable = false)
    private int idBankAccount;

    @Column(name = "balance", nullable = false, length = 45)
    private int balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonbTransient
    private Dresseur dresseur;

    public int getIdBankAccount() {
        return idBankAccount;
    }

    public void setIdBankAccount(int idBankAccount) {
        this.idBankAccount = idBankAccount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Dresseur getDresseur() {
        return dresseur;
    }

    public void setDresseur(Dresseur dresseur) {
        this.dresseur = dresseur;
    }
}
