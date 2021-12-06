package by.bsuir.app.service.impl;

import by.bsuir.app.dao.ModelDao;
import by.bsuir.app.dao.impl.ModelDaoImpl;
import by.bsuir.app.entity.Model;
import by.bsuir.app.service.ModelService;

import java.util.List;

public class ModelServiceImpl implements ModelService {
    private ModelDao markDao = new ModelDaoImpl();


    @Override
    public List<Model> findAllByProductId(Long productId) {
        return null;
    }

    @Override
    public List<Double> findMarksDesignByProductId(Long id) {
        return null;
    }

    @Override
    public Model findPercentProportion() {
        return null;
    }

    @Override
    public boolean save(Model mark) {
        return false;
    }
}
