package by.bsuir.app.dao;

import by.bsuir.app.entity.PersonalData;

import java.util.List;

public interface PersonalDataDao extends BaseDao<Long, PersonalData> {
    public List<Object[]> findAgePercentProportion();

}