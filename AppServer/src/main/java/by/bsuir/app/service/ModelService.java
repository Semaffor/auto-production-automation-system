package by.bsuir.app.service;

import by.bsuir.app.entity.Model;

import java.util.List;

public interface ModelService {
    List<Model> findAllByProductId(Long productId);
    List<Double> findMarksDesignByProductId(Long id);
    public Model findPercentProportion();
    boolean save(Model mark);
}
