package by.bsuir.app.dao;


import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.Role;

import java.util.Optional;

public interface AccountDao extends BaseDao<Account> {
    Role auth(Account account);
    Optional<Account> findByLogin(String login);
}
