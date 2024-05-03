package co.edu.poli.ces.universitas.database;

import co.edu.poli.ces.universitas.dao.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexionMysql {
    // Importante: Tener XAMM, una aplicación que empaqueta varios servicios
    // Con phpMyAdmin puedo administrar las bases de datos

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

        // Polimorfismo
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

    public void createUser(String name, String lastName, String mail,String password){
        String sql = "INSERT INTO USERS(name, lastName, mail, password) VALUES (?,?,?,?)";
        createConexion();
        try {
            PreparedStatement psmt = cnn.prepareStatement(sql);
            psmt.setString(1,name);
            psmt.setString(2, lastName);
            psmt.setString(3,mail);
            psmt.setString(4, password);

            psmt.executeUpdate();

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
    public static void main(String[] args) {
        ConexionMysql conection = new ConexionMysql();
        conection.getUsers();
        //conection.createUser("jhoana","torres aguirre","jhoana@gmail.com","1234");
        //conection.updateUser("Jhoana","Torres Aguirre","jhoana@gmail.com","jhoa");
        //.deleteUser("jhoana@gmail.com");
    }



}
