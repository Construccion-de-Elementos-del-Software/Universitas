package co.edu.poli.ces.universitas.servlet;


import co.edu.poli.ces.universitas.dao.Enrollment;
import co.edu.poli.ces.universitas.repositories.EnrollmentRepository;
import com.google.gson.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "enrollmentsServlet", value = "/enrollments")
public class EnrollmentServlet extends MyServlet {
    private EnrollmentRepository repository;
    private GsonBuilder gsonBuilder;
    private Gson gson;

    public void init()  {
        repository = new EnrollmentRepository();
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }


    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        JsonArray body = this.getJsonArrayFromPost(req);

        if (req.getParameter("idUser") == null) {
            out.print(gson.toJson("Es necesario el id del estudiante."));
            return;
        }
        int index = 1;
        for (JsonElement jsonElement : body) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            if (jsonObject.get("id") == null || jsonObject.get("nameCourse") == null || jsonObject.get("semester") == null || jsonObject.get("status") == null){
                out.print(gson.toJson("La inscripción N°" + index + " no se pudo modificar porque falta información. Recuerde que estos campos son obligatorios: id, nameCourse, semester y status."));
            } else{
                boolean respUpdate = repository.update(new Enrollment(jsonObject.get("id").getAsInt(),jsonObject.get("nameCourse").getAsString(),jsonObject.get("semester").getAsString(),jsonObject.get("status").getAsString(),Integer.parseInt(req.getParameter("idUser"))));
                if (respUpdate){
                    out.print(gson.toJson("La inscripción N°" + index + " se ha modificado correctamente."));
                } else{
                    out.print(gson.toJson("No se pudo modificar la inscripcion N°"+ index +" , verifique que el id del estudiante y del usuario sí existan en la tabla Enrollments."));
                }

            }
            index++;
        }

    }
}
