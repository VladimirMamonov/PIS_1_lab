package dao;

import entities.Exposition;

import java.util.List;

public interface IExpositionDao {

    List<Exposition> findAll();
    Exposition findById(long id);
    long save(Exposition question);
    void update(long id, Exposition question);
    void delete(long id);

}
