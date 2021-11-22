package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.ModelDao;
import by.bsuir.app.entity.Model;
import by.bsuir.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

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
        return false;
    }


}
