package dao;

import entities.Showroom;

import java.util.List;

public interface IShowroomDao {

    List<Showroom> findAll();
    Showroom findById(long id);
    long save(Showroom pack);
    void update(long id, Showroom pack);
    void delete(long id);
}
