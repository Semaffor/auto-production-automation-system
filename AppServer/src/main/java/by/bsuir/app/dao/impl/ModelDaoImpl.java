package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.ModelDao;
import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.Model;
import by.bsuir.app.exception.EmptyObjectException;
import by.bsuir.app.util.HibernateUtil;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

@Log4j2
public class ModelDaoImpl implements ModelDao {
    private static Session session;

    @Override
    public List<Model> findAll() {
        List<Model> models = null;
            session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction();
            models = session.createQuery("SELECT a FROM Model a", Model.class).getResultList();

            session.close();
        return models;
    }

    @Override
    public Optional<Model> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Model model) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Model model) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            if (model == null)
                throw new EmptyObjectException();
            session.saveOrUpdate(model);
            session.close();
        } catch (EmptyObjectException e) {
            session.close();
            return false;
        }
        return true;
    }


    @Override
    public Model findByModelName(String modelName) {
        Model model = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Model.class);
            model = (Model) criteria.add(Restrictions.eq("name", modelName))
                    .uniqueResult();
            session.close();
        } catch (Throwable e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return model;
    }
}
