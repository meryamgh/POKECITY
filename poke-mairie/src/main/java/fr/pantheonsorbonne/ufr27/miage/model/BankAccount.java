package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBankAccount", nullable = false)
    private Integer idBankAccount;

    @Column(name = "balance", nullable = false, length = 45)
    private Integer balance;

    @OneToOne(cascade = CascadeType.ALL)
    private Dresseur dresseur;

    public Integer getIdBankAccount() {
        return idBankAccount;
    }

    public void setIdBankAccount(Integer idBankAccount) {
        this.idBankAccount = idBankAccount;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Dresseur getDresseur() {
        return dresseur;
    }

    public void setDresseur(Dresseur dresseur) {
        this.dresseur = dresseur;
    }
}
