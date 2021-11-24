package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.CarDao;
import by.bsuir.app.dao.ModelDao;
import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.Car;
import by.bsuir.app.exception.DAOException;
import by.bsuir.app.exception.EmptyObjectException;
import by.bsuir.app.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

@Log4j2
public class CarDaoImpl implements CarDao {
    private static Session session;
    private static final String FIND_ALL_MODELS_GROUPED_BY_QUANTITY = "SELECT model_name as model, sum(quantity) as " +
            "quantity FROM model group " +
            "by model_name";

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

            if (car.getModel().getId() == null) {
                ModelDao modelDao = new ModelDaoImpl();

                car.setModel(modelDao.findByModelName(car.getModel().getName()));
            }

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(car);
            session.getTransaction().commit();
            session.close();
        } catch (EmptyObjectException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean save(Car car) {
        try {
            if (car == null)
                throw new EmptyObjectException();
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(car);
            session.getTransaction().commit();
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

        SQLQuery query = session.createSQLQuery(FIND_ALL_MODELS_GROUPED_BY_QUANTITY);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List list = query.list();

        session.close();
        return list;
    }


    @Override
    public boolean deleteByVIN(String VIN) {
       Optional<Car> car = findByVIN(VIN);
        try {
            if (car.isEmpty())
                throw new EmptyObjectException();
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(car);
            session.close();
        } catch (EmptyObjectException e) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<Car> findByVIN(String VIN) {
        Optional<Car> car = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Car.class);
            car = Optional.ofNullable((Car) criteria.add(Restrictions.eq("VIN", VIN))
                    .uniqueResult());
            session.close();
        } catch (Throwable e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return car;
        }
        return car;
    }
}
