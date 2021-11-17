package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AccountDao;
import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.Role;
import by.bsuir.app.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

@Log4j2
public class AccountDaoImpl implements AccountDao {
    private static Session session;

    @Override
    public Optional<List<Account>> findAll() {
        Optional<List<Account>> accounts = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            accounts = Optional.of(session.createQuery("SELECT a FROM Account a", Account.class).getResultList());
            session.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account findById(Long id) {
        Account bs = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            bs = session.load(Account.class, id);
            session.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return bs;
    }

    @Override
    public boolean delete(Account account) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(account);
            session.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        Account account = new Account();
        account.setId(id);
        try {
            delete(account);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean saveOrUpdate(Account account) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(account);
        session.close();
        return true;
    }

    @Override
    public Role auth(Account account) {
        Account foundAccount = findByLogin(account.getLogin()).orElse(new Account());

        if (account.getLogin().equals(foundAccount.getLogin()) && account.getPassword().equals(foundAccount.getPassword()))
            for (Role e : Role.values()) {
                if (e == foundAccount.getRole())
                    return e;
            }
        return Role.UNDEFINED;
    }

    @Override
    @SuppressWarnings("depricated")
    public Optional<Account> findByLogin(String login) {
        Optional<Account> account = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Account.class);
            account = Optional.of((Account) criteria.add(Restrictions.eq("login", login))
                    .uniqueResult());
            session.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return account;
    }
}