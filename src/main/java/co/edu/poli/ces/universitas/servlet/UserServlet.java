package co.edu.poli.ces.universitas.servlet;


import co.edu.poli.ces.universitas.dao.User;
import co.edu.poli.ces.universitas.database.ConexionMysql;
import co.edu.poli.ces.universitas.repositories.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.commons.codec.digest.DigestUtils;

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
public class UserServlet extends MyServlet {

    private UserRepository repository;
    //JSON
    private GsonBuilder gsonBuilder;
    private Gson gson;

    public void init()  {
        repository = new UserRepository();
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        if (req.getParameter("id") == null){
            List<User> listUsers = repository.getUsers();
            out.print(gson.toJson(listUsers));
        } else {
            User usuario = repository.getUsers(Integer.parseInt(req.getParameter("id")));
            out.print(gson.toJson(usuario));
        }
        out.flush();
    }

    /*
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        JsonObject body =  this.getParamsFromPost(req);
        if (body.get("name") == null || body.get("lastName") == null || body.get("mail") == null || body.get("password") == null){
            resp.setStatus(400);
            out.print(gson.toJson("Falta informacion. Recuerde que estos campos son obligatorios: name, lastName, mail y password."));
        } else {
          User user = cnn.getUserByMail(body.get("mail").getAsString());
          if (user != null){
              resp.setStatus(200);
              out.print(gson.toJson(user));
          } else {
              // MD5 password with a dependency: commons-codec
              String md5Password = DigestUtils.md5Hex(body.get("password").getAsString());
              int id  = cnn.createUser(body.get("name").getAsString(),body.get("lastName").getAsString(),body.get("mail").getAsString(),md5Password);
              if (id != 0){
                  resp.setStatus(201);
                  out.print(gson.toJson(cnn.getUsers(id)));
              } else {
                  out.print(gson.toJson("Ha ocuerrdido un error"));
              }

          }
        }
        out.flush();
    }*/
}
