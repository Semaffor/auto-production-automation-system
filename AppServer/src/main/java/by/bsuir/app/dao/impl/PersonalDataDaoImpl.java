package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.PersonalDataDao;
import by.bsuir.app.entity.PersonalData;
import by.bsuir.app.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonalDataDaoImpl implements PersonalDataDao {
    private static Session session;
    private static final String FIND_AGE_PERCENT_PROPORTION = "SELECT age as ageGroup, count(*) as quantity FROM " +
            "personal_data " +
            "group by age; ";

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

    @Override
    public List<Object[]> findAgePercentProportion() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        SQLQuery query = session.createSQLQuery(FIND_AGE_PERCENT_PROPORTION);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

        List list = query.list();

        session.close();
        return list;
    }
}
