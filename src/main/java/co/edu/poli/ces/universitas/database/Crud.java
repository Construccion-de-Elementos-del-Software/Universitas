package co.edu.poli.ces.universitas.database;

import java.util.List;

public interface Crud<E,T> {
    public void insert(E user);
    public boolean update(E user);
    public List<E> get();
    public E getOne(T id);
}

