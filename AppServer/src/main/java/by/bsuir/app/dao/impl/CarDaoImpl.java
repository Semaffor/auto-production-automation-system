package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.CarDao;
import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.Car;
import by.bsuir.app.exception.DAOException;
import by.bsuir.app.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {
    private static Session session;

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public Optional<Car> findById(Long id) {
        Optional<Car> bs = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            bs = Optional.of(session.load(Car.class, id));
            session.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return bs;
    }

    @Override
    public boolean delete(Car car) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Car car) {
        return false;
    }

    @Override
    public List<Car> findAllByField(String field) throws DAOException {
        return null;
    }
}
