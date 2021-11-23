package by.bsuir.app.dao;

import by.bsuir.app.entity.Model;

import java.util.List;

public interface ModelDao extends BaseDao<Long, Model>{
    Model findByModelName(String model);
}
