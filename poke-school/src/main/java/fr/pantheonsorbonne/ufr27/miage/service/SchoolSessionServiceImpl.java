package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.SchoolSessionDao;
import fr.pantheonsorbonne.ufr27.miage.dao.SchoolSessionDaoImpl;
import fr.pantheonsorbonne.ufr27.miage.model.SchoolSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;

@ApplicationScoped
public class SchoolSessionServiceImpl implements SchoolSessionService {

    @Inject
    SchoolSessionDao schoolSessionDao;

    @Override
    public Collection<SchoolSession> getAllSessions() {
        return schoolSessionDao.getSchoolSessions(); }
}
