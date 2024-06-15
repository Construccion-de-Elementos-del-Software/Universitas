package co.edu.poli.ces.universitas.repositories;

import co.edu.poli.ces.universitas.dao.User;
import co.edu.poli.ces.universitas.database.ConexionMysql;
import co.edu.poli.ces.universitas.database.Crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends ConexionMysql implements Crud<User> {


    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List get() {
        String sql = "SELECT * FROM USERS";

        List<User> users = new ArrayList<>();
        try {
            createConexion();
            Statement stmt = getCnn().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            while (result.next()){
                users.add(new User(result.getInt("id"),result.getString("name"),result.getString("lastName"),result.getString("mail"),result.getString("password"),result.getDate("createdAt"),result.getDate("updatedAt"),result.getDate("deletedAt")));
            }
            stmt.close();
            result.close();
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                getCnn().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public User getOne(int id) {
        String sql = "SELECT * FROM USERS WHERE ID = ?";
        try {
            createConexion();
            PreparedStatement pst = getCnn().prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                return new User(result.getInt("id"), result.getString("name"), result.getString("lastName"), result.getString("mail"), result.getString("password"), result.getDate("createdAt"), result.getDate("updatedAt"), result.getDate("deletedAt"));
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (getCnn() != null)
                    getCnn().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
