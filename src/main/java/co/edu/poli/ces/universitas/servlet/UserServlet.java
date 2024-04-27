package co.edu.poli.ces.universitas.servlet;


import co.edu.poli.ces.universitas.dao.User;
import co.edu.poli.ces.universitas.database.ConexionMysql;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "userServlet", value = "/user")
public class UserServlet extends HttpServlet {

    private ConexionMysql cnn;
    //JSON
    private GsonBuilder gsonBuilder;
    private Gson gson;

    public void init()  {
        cnn = new ConexionMysql();
        System.out.println("Inicia servlet de estudiante");
        // JSON
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // REQ todo lo que envio desde el cliente
        // RES me permite responder al cliente
        /* SIN JSON
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("Hola desde el servlet de estudiantes");
        out.print( "<b>DNI: </b>" + req.getParameter("dni"));

        List<User> listUsers = cnn.getUsers();
        out.print("<table>" +
                "<thead>" +
                "<tr>" +
                "<th>Nombre</th>"+
                "<th>Apellidos</th>"+
                "</tr>" +
                "</thead>" +
                "<tbody>");

        for (User u: listUsers){
            out.print("<tr>" +
                    "<td>" + u.getName() +
                                    "</td>"+
                            "<td>" + u.getLastName() +
                            "</td>" +
                            "</tr>");
        }
        out.print(
                "</tbody>" +
                "</table>");
        */

        /*COM JSON
           Tener la dependencia GSON
        * */
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(cnn.getUsers()));
        out.flush();
    }
}
