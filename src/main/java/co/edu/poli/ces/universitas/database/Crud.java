package co.edu.poli.ces.universitas.database;

import co.edu.poli.ces.universitas.dao.User;

import java.util.List;

public interface Crud {
    public void insert(User user);
    public void update(User user);
    public List<User> get();
}
