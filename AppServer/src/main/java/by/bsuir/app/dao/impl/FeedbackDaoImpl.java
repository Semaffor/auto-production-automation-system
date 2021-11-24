package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AccountDao;
import by.bsuir.app.dao.FeedbackDao;
import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.Feedback;
import by.bsuir.app.exception.DAOException;
import by.bsuir.app.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
public class FeedbackDaoImpl implements FeedbackDao {
    private static Session session;

    @Override
    public List<Feedback> findAll() {
        List<Feedback> feedbacks = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            feedbacks = session.createQuery("SELECT a FROM Feedback a", Feedback.class).getResultList();
            session.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return feedbacks;
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
        feedback.setQuestionDate(new Date(System.currentTimeMillis()));
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(feedback);
        session.close();
        return true;
    }


    @Override
    public List<Feedback> findAllByUserLogin(String login) throws DAOException {
        AccountDao accountDao = new AccountDaoImpl();
        Account account = accountDao.findByLogin(login);

        List<Feedback> feedbacks = null;

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria cr = session.createCriteria(Feedback.class);
        cr.add(Restrictions.eq("sender_id", account.getId()));
        feedbacks = (List<Feedback>) cr.list();
        session.close();

        return feedbacks;
    }

    @Override
    public boolean saveAnswer(Feedback feedback) {
//        Feedback feedbackWithId = findByQuestion(feedback.getQuestion());
//        feedbackWithId.setAnswer(feedback.getAnswer());
        feedback.setAnswerDate(new Date(System.currentTimeMillis()));
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(feedback);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Feedback findByQuestion(String question) {
        Optional<Feedback> feedbackWithId = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Feedback.class);
            feedbackWithId = Optional.of((Feedback) criteria.add(Restrictions.like("question",
                    question)).uniqueResult());
            session.close();
        } catch (Throwable e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new Feedback();
        }
        return feedbackWithId.orElse(new Feedback());
    }
}
