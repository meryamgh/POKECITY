package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.SchoolGateway;
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
    SchoolGateway gateway;

    private Pokemon pokemon;

    @Override
    public Collection<SchoolSession> getAllSessions() {
        return schoolSessionDao.getSchoolSessions(); }

    @Override
    public Collection<SchoolTicket> getAllTickets() {
        return schoolTicketDao.getSchoolTickets();
    }


    @Transactional
    @Override
    public void inscrirePokemon(SchoolSession session) {

        //on récupère l'id du pokemon
        int idPokemon = this.pokemon.getIdPokemon();

        //on récupère l'id de la session
        int idSession = session.getIdSchoolSession();

        //on crée un ticket pour garder l'historique des cours effectués par un pokemon
        schoolTicketDao.createSchoolTicket(idPokemon, idSession);

        //on augmente le score du pokemon qui a suivi le cours
        int gain = session.getPokescoreGain();
        int newScore = this.pokemon.getPokeScore() + gain;
        this.pokemon.setPokeScore(newScore);

        //on renvoie le pokemon improved à la mairie
        gateway.sendImprovedPokemonToMairie(this.pokemon);

        //on remet le pokemon à null une fois que la session est finie
        resetPokemon();
    }

    @Override
    public boolean isMoneyEnough() {
        return true;
    }

    @Override
    public SchoolSession findRightSession() {
        int score = pokemon.getPokeScore();
        int idRightSession;
        if (score < 70) {
            idRightSession = 1;
        } else if (score < 110) {
            idRightSession = 2;
        } else {
            idRightSession = 3;
        }

        return schoolSessionDao.getSchoolSessionById(idRightSession);
    }

    @Override
    public void sendSessionToMairie(SchoolSession session){
        gateway.sendRightSessionToMairie(session);
    }

    @Override
    public void collectPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon){
        Pokemon p = new Pokemon();
        p.setPokeScore(pokemon.pokeScore());
        p.setIdPokemon(pokemon.idPokemon());
        this.pokemon = p;
    }

    @Override
    public Pokemon getPokemon() {
        return this.pokemon;
    }

    @Override
    public void resetPokemon() {
        this.pokemon = null;
    }


}