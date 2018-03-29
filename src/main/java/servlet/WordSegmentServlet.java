package servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import dao.SimpleWordSegment;
import dao.WordSegment;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "WordSegmentServlet", value = "/WordSegmentServlet")
public class WordSegmentServlet extends HttpServlet {

    private WordSegment wordSegment = new SimpleWordSegment();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String method = request.getParameter("method");

        String string = request.getParameter("string");

        List<String> result = new ArrayList<>();

        switch (method) {
            case "MM":
                result = wordSegment.MM(string);
                break;
        }

        JsonObject json = new JsonObject();

        JsonArray data = new JsonArray();
        for (String str : result) {
            data.add(new JsonPrimitive(str));
        }

        json.addProperty("status", true);
        json.add("data", data);

        response.getWriter().write(json.toString());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.print("do get");
        doPost(request, response);
    }
}
