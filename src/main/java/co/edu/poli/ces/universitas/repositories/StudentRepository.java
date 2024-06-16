package co.edu.poli.ces.universitas.repositories;


import co.edu.poli.ces.universitas.dao.Student;
import co.edu.poli.ces.universitas.database.ConexionMysql;
import co.edu.poli.ces.universitas.database.Crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentRepository extends ConexionMysql implements Crud<Student,String> {

    @Override
    public void insert(Student user) {

    }

    @Override
    public boolean update(Student user) {
        return false;
    }

    @Override
    public List<Student> get() {
        return null;
    }

    @Override
    public Student getOne(String id) {
        String sql = "SELECT * FROM STUDENTS WHERE mail = ?";
        try {
            createConexion();
            PreparedStatement pst = getCnn().prepareStatement(sql);
            pst.setString(1, id);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                return new Student(result.getInt("id"), result.getString("name"), result.getString("lastName"), result.getString("mail"), result.getDate("createdAt"), result.getDate("updatedAt"), result.getDate("deletedAt"));
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
