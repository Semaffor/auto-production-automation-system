package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.HistoryLogDao;
import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.Feedback;
import by.bsuir.app.entity.HistoryLog;
import by.bsuir.app.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class HistoryLogDaoImpl implements HistoryLogDao {
    private static Session session;

    @Override
    public List<HistoryLog> findAll() {
        return null;
    }

    @Override
    public Optional<HistoryLog> findById(Long id) {
        Optional<HistoryLog> bs = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            bs = Optional.of(session.load(HistoryLog.class, id));
            session.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return bs;
    }

    @Override
    public boolean delete(HistoryLog historyLog) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(HistoryLog historyLog) {
        return false;
    }
}
