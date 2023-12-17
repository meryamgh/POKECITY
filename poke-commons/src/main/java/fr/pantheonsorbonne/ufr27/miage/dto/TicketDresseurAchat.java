package fr.pantheonsorbonne.ufr27.miage.dto;


import java.io.Serializable;

public record TicketDresseurAchat(int prixAchat, int idDresseur, boolean isValidateTicket) implements Serializable {
}
