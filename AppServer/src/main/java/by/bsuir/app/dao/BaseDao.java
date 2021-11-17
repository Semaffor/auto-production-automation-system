package by.bsuir.app.dao;

import by.bsuir.app.entity.Account;

import java.util.List;
import java.util.Optional;

public interface BaseDao <T> {
    Optional<List<T>> findAll();
    Account findById(Long id) ;
    boolean delete(T t) ;
    boolean deleteById(Long id) ;
    boolean saveOrUpdate(T t) ;
    //boolean update(T t) ;
}
