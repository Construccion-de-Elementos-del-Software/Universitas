package co.edu.poli.ces.universitas.servlet;


import co.edu.poli.ces.universitas.dao.Comment;
import co.edu.poli.ces.universitas.dao.Reply;
import co.edu.poli.ces.universitas.dao.TypesComment;
import co.edu.poli.ces.universitas.dao.User;
import co.edu.poli.ces.universitas.repositories.CommentRepository;
import co.edu.poli.ces.universitas.repositories.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet(name = "userServlet", value = "/comment")
public class CommentServlet extends MyServlet{
    private CommentRepository repository;
    private GsonBuilder gsonBuilder;
    private Gson gson;

    public void init()  {
        repository = new CommentRepository();
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        JsonObject body = this.getParamsFromPost(req);

        if (body.get("mail") == null || body.get("fullName") == null || body.get("type") == null || body.get("comment_text") == null ){
            resp.setStatus(400);
            out.print(gson.toJson("Falta información. Estos datos son requeridos: mail, fullName, type y comment_text"));
        } else{
            JsonElement typeComment = body.get("type");
            if (TypesComment.valueOf(typeComment.getAsString()) == TypesComment.COMMENT){
                repository.insert(new Comment(body.get("mail").getAsString(),body.get("fullName").getAsString(),new Date(),TypesComment.valueOf(typeComment.getAsString()),body.get("comment_text").getAsString()));
                resp.setStatus(201);
                out.print(gson.toJson("Comentario creado."));
            } else if (TypesComment.valueOf(typeComment.getAsString()) == TypesComment.REPLY){
                if (body.get("documentComment")==null){
                    resp.setStatus(400);
                    out.print(gson.toJson("Como es una respuesta a un comentario, necesitamos el documento del comentario."));
                } else {
                    boolean respt = repository.insertReply(body.get("documentComment").getAsString(),new Reply(body.get("mail").getAsString(),body.get("fullName").getAsString(),new Date(),TypesComment.valueOf(typeComment.getAsString()),body.get("comment_text").getAsString()));
                    if (respt){
                        resp.setStatus(201);
                        out.print(gson.toJson("Se ha hecho una replica al comentario."));
                    } else{
                        resp.setStatus(400);
                        out.print(gson.toJson("Algo salió mal. No se hizo la replica. Verifique que el documentComment sí exista."));
                    }


                }
            }
        }
        out.flush();
    }
}
