package co.edu.poli.ces.universitas.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public abstract class MyServlet extends HttpServlet {
    public JsonObject getParamsFromPost(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();

        return JsonParser.parseString(sb.toString()).getAsJsonObject();
    }

    public JsonArray getJsonArrayFromPost(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line).append("\n");
            line = reader.readLine();
        }
        reader.close();

        return JsonParser.parseString(sb.toString()).getAsJsonArray();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equals("PATCH")){
            this.doPatch(req,res);
        }else {
            super.service(req, res);
        }

    }

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String protocol = req.getProtocol();
        String msg = "mensaje";
        if (protocol.endsWith("1.1")) {
            resp.sendError(405, msg);
        } else {
            resp.sendError(400, msg);
        }
    }
}
