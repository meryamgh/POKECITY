package fr.pantheonsorbonne.ufr27.miage.services;

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
        int idRightSession = (pokescore < 70) ? 1 : (pokescore < 110) ? 2 : 3;
        return schoolSessionDao.getSchoolSessionById(idRightSession);
    }


}
