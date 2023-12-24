package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.SchoolSessionDao;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;

@ApplicationScoped
public class SchoolSessionServiceImpl implements SchoolSessionService{

    @Inject
    SchoolSessionDao schoolSessionDao;

    @Override
    public Collection<SchoolSession> getAllSessions() {
        return schoolSessionDao.getSchoolSessions();
    }

    @Override
    public SchoolSession findRightSession(int pokescore) {
        int idRightSession;
        if (pokescore < 70) {
            idRightSession = 1;
        } else if (pokescore < 110) {
            idRightSession = 2;
        } else {
            idRightSession = 3;
        }

        return schoolSessionDao.getSchoolSessionById(idRightSession);
    }


}
