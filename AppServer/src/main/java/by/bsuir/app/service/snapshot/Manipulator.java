package by.bsuir.app.service.snapshot;

import by.bsuir.app.dao.CarDao;
import by.bsuir.app.dao.impl.BaseDao;
import by.bsuir.app.dao.impl.CarDaoImpl;
import by.bsuir.app.entity.BaseEntity;
import by.bsuir.app.entity.Car;

import java.util.List;

import static by.bsuir.app.util.constants.Constants.MYSQL_TABLE_NAME_CAR;

public class Manipulator {
    private Originator originator = new Originator();
    private Caretaker caretaker = new Caretaker();
    private CarDao carDao = new CarDaoImpl();
    private BaseDao baseDao = new BaseDao();

    public boolean saveObjectInMemory(Car car) {
        List<Car> cars = carDao.findAll();

        originator.setStorage(cars);

        caretaker.setMemento(originator.createMemento());
        System.out.println(caretaker.getMemento().getStorage());

        return true;
    }

    public boolean getObjectFromMemory(Car car) {
        Memento storage = caretaker.getMemento();
        List<Car> cars = (List<Car>) storage.getStorage();
        if (car == null)
            return false;
        baseDao.hqlTruncate(MYSQL_TABLE_NAME_CAR);

        System.out.println(cars);
        for (Car m : cars)
            carDao.save(m);

        return true;
    }
}
