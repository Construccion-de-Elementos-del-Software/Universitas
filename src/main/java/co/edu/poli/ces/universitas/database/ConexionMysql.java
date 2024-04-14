package co.edu.poli.ces.universitas.database;

import java.sql.*;

public class ConexionMysql {
    // Importante: Tener XAMM, una aplicación que empaqueta varios servicios
    // Con phpMyAdmin puedo administrar las bases de datos

    private String user;
    private String password;
    private int port;
    private String host;

    private String nameDatabase;
    private Connection cnn;

    ConexionMysql (){
        user = "root";
        password = "";
        port = 3306;
        host = "localhost";
        nameDatabase = "universitas";
    }

    public void createConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+nameDatabase, user, password);
            System.out.println("Successful connection.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("An error occurred during the connection.");
            throw new RuntimeException(e);
        }
    }

    public void getUsers()  {
        String sql = "SELECT * FROM USERS";
        try {
            createConexion();
            Statement stmt = cnn.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            while (result.next()){
                System.out.println("***************************");
                System.out.println("ID: "+result.getInt(1));
                System.out.println("Name: "+result.getString(2));
                System.out.println("Lastname: "+result.getString("lastName"));
                System.out.println("****************************");
            }
            stmt.close();
            result.close();
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
    public static void main(String[] args) {
        ConexionMysql conection = new ConexionMysql();
        conection.getUsers();
    }



}
