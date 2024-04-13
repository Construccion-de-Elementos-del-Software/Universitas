package co.edu.poli.ces.universitas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public Connection createConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+nameDatabase, user, password);
            System.out.println("Successful connection.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("An error occurred during the connection.");
            throw new RuntimeException(e);
        }
        return  null;
    }

    public static void main(String[] args) {
        ConexionMysql conection = new ConexionMysql();
        conection.createConexion();
    }



}
