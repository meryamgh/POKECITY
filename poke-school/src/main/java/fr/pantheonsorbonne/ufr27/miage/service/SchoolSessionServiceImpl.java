package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.SchoolGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.PokemonDao;
import fr.pantheonsorbonne.ufr27.miage.dao.SchoolSessionDao;
import fr.pantheonsorbonne.ufr27.miage.dao.SchoolTicketDao;
import fr.pantheonsorbonne.ufr27.miage.model.Pokemon;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolSession;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolTicket;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Collection;

@ApplicationScoped
public class SchoolSessionServiceImpl implements SchoolSessionService {

    @Inject
    SchoolSessionDao schoolSessionDao;

    @Inject
    SchoolTicketDao schoolTicketDao;

    @Inject
    PokemonDao pokemonDao;

    @Inject
    SchoolGateway gateway;

    @Override
    public Collection<SchoolSession> getAllSessions() {
        return schoolSessionDao.getSchoolSessions(); }

    @Override
    public Collection<SchoolTicket> getAllTickets() {
        return schoolTicketDao.getSchoolTickets();
    }


    @Transactional
    @Override
    public void inscrirePokemon(int idPokemon, int idSession) {

        //on crée un ticket pour garder l'historique des cours effectués par un pokemon
        schoolTicketDao.createSchoolTicket(idPokemon, idSession);

        //on augmente le score du pokemon qui a suivi le cours
        int gain = schoolSessionDao.getSchoolSessionGainById(idSession);
        int newScore = pokemonDao.getPokemonScoreById(idPokemon) + gain;
        pokemonDao.setPokescorebyId(idPokemon, newScore);

        //on renvoie le pokemon improved à la mairie
        Pokemon improvedPokemon = pokemonDao.getPokemonById(idPokemon);
        gateway.sendImprovedPokemonToMairie(improvedPokemon);
    }

    @Override
    public boolean isMoneyEnough() {
        return true;
    }


}
