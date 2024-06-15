package co.edu.poli.ces.universitas.database;

import co.edu.poli.ces.universitas.dao.User;

import java.util.List;

public interface Crud<E> {
    public void insert(E user);
    public void update(E user);
    public List<E> get();
    public E getOne(int id);
}

