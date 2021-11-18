package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.PositionDao;
import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.PersonalData;
import by.bsuir.app.entity.Position;
import by.bsuir.app.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class PositionDaoImpl implements PositionDao {
    private static Session session;

    @Override
    public List<Position> findAll() {
        return null;
    }

    @Override
    public Optional<Position> findById(Long id) {
        Optional<Position> bs = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            bs = Optional.of(session.load(Position.class, id));
            session.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return bs;
    }

    @Override
    public boolean delete(Position position) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Position position) {
        return false;
    }
}
