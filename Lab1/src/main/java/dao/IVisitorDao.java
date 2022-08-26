package dao;

import entities.Visitor;

import java.util.List;

public interface IVisitorDao {

    List<Visitor> findAll();
    Visitor findById(long id);
    long save(Visitor visitor);
    void update(long id, Visitor visitor);
    void delete(long id);
}
