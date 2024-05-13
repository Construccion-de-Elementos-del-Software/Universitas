package co.edu.poli.ces.universitas.database;

import co.edu.poli.ces.universitas.dao.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexionMysql {
    private String user;
    private String password;
    private int port;
    private String host;

    private String nameDatabase;
    private Connection cnn;

    public ConexionMysql(){
        user = "root";
        password = "";
        port = 3306;
        host = "localhost";
        nameDatabase = "universitas";
    }

    private void createConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+nameDatabase, user, password);
            System.out.println("Successful connection.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("An error occurred during the connection.");
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsers()  {
        String sql = "SELECT * FROM USERS";

        List<User> users = new ArrayList<>();
        try {
            createConexion();
            Statement stmt = cnn.createStatement();
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
                cnn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public int createUser(String name, String lastName, String mail,String password){
        String sql = "INSERT INTO USERS(name, lastName, mail, password) VALUES (?,?,?,?)";
        int id = 0;
        createConexion();
        try {
            PreparedStatement psmt = cnn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1,name);
            psmt.setString(2, lastName);
            psmt.setString(3,mail);
            psmt.setString(4, password);

            psmt.executeUpdate();
            ResultSet resultSet = psmt.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            System.out.println("User " +name+" has been created.");
            psmt.close();
        } catch (SQLException e) {
            System.out.println("Error "+e.getErrorCode()+ " " +e.getMessage());
        } finally {
            try {
                cnn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return id;

    }

    public void updateUser(String name,String lastName, String mail, String password){
        String sql = "UPDATE USERS SET name=?, lastName=?, password=? WHERE mail=?";
        createConexion();
        try {
            PreparedStatement psmt = cnn.prepareStatement(sql);
            psmt.setString(1,name);
            psmt.setString(2,lastName);
            psmt.setString(3,password);
            psmt.setString(4,mail);

            psmt.executeUpdate();
            System.out.println("User has been updated.");

            psmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                cnn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteUser(String mail){
        String sql = "UPDATE users set deletedAt = current_timestamp() where mail = ?";
        createConexion();
        try {
            PreparedStatement stmt = cnn.prepareStatement(sql);
            stmt.setString(1,mail);

            int rowsAfected = stmt.executeUpdate();

            if (rowsAfected > 0){
                System.out.println("User has been deleted");
            } else {
                System.out.println("An error has occurred, user does not exist.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public User getUsers(int id) {
        String sql = "SELECT * FROM USERS WHERE ID = ?";
        try {
            createConexion();
            PreparedStatement pst = cnn.prepareStatement(sql);
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
                if (cnn != null)
                    cnn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public User getUserByMail(String mail) {
        String sql = "SELECT * FROM USERS WHERE mail = ?";
        User user = null;
        try {
            createConexion();
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, mail);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                user = new User(result.getInt("id"), result.getString("name"), result.getString("lastName"), result.getString("mail"), result.getString("password"), result.getDate("createdAt"), result.getDate("updatedAt"), result.getDate("deletedAt"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (cnn != null)
                    cnn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return user;
    }
}
