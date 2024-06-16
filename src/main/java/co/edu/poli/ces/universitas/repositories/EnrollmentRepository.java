package co.edu.poli.ces.universitas.repositories;

import co.edu.poli.ces.universitas.dao.Enrollment;
import co.edu.poli.ces.universitas.database.ConexionMysql;
import co.edu.poli.ces.universitas.database.Crud;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EnrollmentRepository extends ConexionMysql implements Crud<Enrollment,Integer> {
    @Override
    public void insert(Enrollment user) {

    }

    @Override
    public boolean update(Enrollment enrollment) {
        String sql = "UPDATE ENROLLMENTS SET nameCourse=?, semester=?, status=? WHERE id=? AND id_student=?";
        try {
            createConexion();
            PreparedStatement psmt = getCnn().prepareStatement(sql);
            psmt.setString(1,enrollment.getNameCourse());
            psmt.setString(2,enrollment.getSemester());
            psmt.setString(3,enrollment.getStatus());
            psmt.setInt(4,enrollment.getId());
            psmt.setInt(5,enrollment.getId_student());
            int row = psmt.executeUpdate();
            psmt.close();

            return row > 0;
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
    public List<Enrollment> get() {
        return null;
    }

    @Override
    public Enrollment getOne(Integer id) {
        return null;
    }
}
