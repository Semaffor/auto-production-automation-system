package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.FeedbackDao;
import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.Car;
import by.bsuir.app.entity.Feedback;
import by.bsuir.app.exception.DAOException;
import by.bsuir.app.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class FeedbackDaoImpl implements FeedbackDao {
    private static Session session;

    @Override
    public List<Feedback> findAll() {
        return null;
    }

    @Override
    public Optional<Feedback> findById(Long id) {
        Optional<Feedback> bs = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            bs = Optional.of(session.load(Feedback.class, id));
            session.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return bs;
    }

    @Override
    public boolean delete(Feedback feedback) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Feedback feedback) {
        return false;
    }

    @Override
    public List<Feedback> findAllByField(String field) throws DAOException {
        return null;
    }
}
