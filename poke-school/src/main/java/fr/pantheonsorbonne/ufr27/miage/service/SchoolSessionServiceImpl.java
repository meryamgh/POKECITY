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
    SchoolGateway gateway;

    Pokemon pokemon;

    @Override
    public Collection<SchoolSession> getAllSessions() {
        return schoolSessionDao.getSchoolSessions(); }

    @Override
    public Collection<SchoolTicket> getAllTickets() {
        return schoolTicketDao.getSchoolTickets();
    }


    @Transactional
    @Override
    public void inscrirePokemon(Pokemon pokemon, SchoolSession session) {

        //on récupère l'id du pokemon
        int idPokemon = pokemon.getIdPokemon();

        //on récupère l'id de la session
        int idSession = session.getIdSchoolSession();

        //on crée un ticket pour garder l'historique des cours effectués par un pokemon
        schoolTicketDao.createSchoolTicket(idPokemon, idSession);

        //on augmente le score du pokemon qui a suivi le cours
        int gain = session.getPokescoreGain();
        int newScore = pokemon.getPokeScore() + gain;
        pokemon.setPokeScore(newScore);

        //on renvoie le pokemon improved à la mairie
        gateway.sendImprovedPokemonToMairie(pokemon);
    }

    @Override
    public boolean isMoneyEnough() {
        return true;
    }

    @Override
    public SchoolSession findRightSession(Pokemon pokemon) {
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
    public void getPokemon(fr.pantheonsorbonne.ufr27.miage.dto.Pokemon pokemon){
        int id = pokemon.idPokemon();
        int pokescore = pokemon.pokeScore();
        Pokemon p = new Pokemon();
        p.setIdPokemon(id);
        p.setPokeScore(pokescore);
        this.pokemon = p;
    }
}