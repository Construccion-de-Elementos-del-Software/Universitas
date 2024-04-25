package co.edu.poli.ces.universitas.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "userServlet", value = "/user")
public class UserServlet extends HttpServlet {

    public void init()  {
        System.out.println("Inicia servlet de estudiante");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // REQ todo lo que envio desde el cliente
        // RES me permite responder al cliente
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("Hola desde el servlet de estudiantes");
        out.flush();
    }
}
