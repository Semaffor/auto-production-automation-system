package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.PersonalDataDao;
import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.HistoryLog;
import by.bsuir.app.entity.PersonalData;
import by.bsuir.app.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class PersonalDataDaoImpl implements PersonalDataDao {
    private static Session session;

    @Override
    public List<PersonalData> findAll() {
        return null;
    }

    @Override
    public Optional<PersonalData> findById(Long id) {
        Optional<PersonalData> bs = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            bs = Optional.of(session.load(PersonalData.class, id));
            session.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return bs;
    }

    @Override
    public boolean delete(PersonalData personalDate) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(PersonalData personalDate) {
        return false;
    }
}
