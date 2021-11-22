package by.bsuir.app.dao;

import by.bsuir.app.entity.Car;
import by.bsuir.app.exception.DAOException;

import java.util.List;

public interface CarDao extends BaseDao<Long, Car> {
    /**
     * Method that use to find all requests on db by special field
     * @param field - parameter by which requests are searching on db
     * @return list of requests with a necessary field
     */
    List<Car> findAllByField(String field) throws DAOException;
    List<Object[]>  findAllGroupedByQuantity();

}
