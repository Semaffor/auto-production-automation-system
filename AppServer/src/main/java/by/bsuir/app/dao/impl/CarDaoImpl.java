package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.CarDao;
import by.bsuir.app.entity.Car;
import by.bsuir.app.exception.DAOException;
import by.bsuir.app.exception.EmptyObjectException;
import by.bsuir.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {
    private static Session session;
    private static final String FIND_ALL_MODELS_GROUPED_BY_QUANTITY = "SELECT (SELECT m.name FROM Model m WHERE m.id " +
            "=id) as model," +
            "sum(i.quantity) " +
            "as " +
            "quantity " +
            "FROM" +
            " Car i GROUP BY model";

    //SELECT (SELECT name FROM Model WHERE id" +
    //            " = i.model.id) as model, sum(i.quantity) as quantity FROM Car i GROUP BY model";
    @Override
    public List<Car> findAll() {
        List<Car> cars = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        cars = session.createQuery("SELECT a FROM Car a", Car.class).getResultList();
        session.close();
        return cars;
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
        try {
            if (car == null)
                throw new EmptyObjectException();
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(car);
            session.close();
        } catch (EmptyObjectException e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Car> findAllByField(String field) throws DAOException {
        return null;
    }

    @Override
    public List<Object[]> findAllGroupedByQuantity() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery(FIND_ALL_MODELS_GROUPED_BY_QUANTITY);
        List<Object[]> list = query.getResultList();

        session.close();
        return list;
    }
}
