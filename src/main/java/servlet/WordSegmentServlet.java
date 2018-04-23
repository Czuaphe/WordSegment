package servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import dao.SimpleWordSegment;
import dao.WordSegment;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(name = "WordSegmentServlet", value = "/WordSegmentServlet")
public class WordSegmentServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(WordSegmentServlet.class);

    private WordSegment wordSegment = new SimpleWordSegment();

    private JsonObject json = new JsonObject();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");

        String returnType = "JSON";

        String string = request.getParameter("text");

        switch (returnType) {
            case "JSON":
                // 分词结果转换成JSON数据
                segment2Json(string);
                logger.debug("要返回的JSON数据为：" + json.toString());
                response.getWriter().write(json.toString());
                break;
            case "JSP":

                break;
            default:

                break;
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        logger.debug("使用do get方法");
        doPost(request, response);
    }

    /**
     * 分词算法
     * @param text
     */
    public void segment2Json(String text) {

        String[] methods = {"MM", "RMM", "BM", "MC"};

        if (text == null || text.equals("")) {
            json.addProperty("status", false);
            json.addProperty("error", "分词字符串为空！");
            return ;
        }

        JsonArray array = new JsonArray();

        for (String method : methods) {
            JsonObject result = new JsonObject();
            // 算法的名称
            result.addProperty("name", method);
            long start = System.currentTimeMillis();
            List<String> data = string2dataSegment(method, text);
            long end = System.currentTimeMillis();
            JsonArray dataSegment = new JsonArray();
            if (data != null && data.size() != 0) {
                StringBuffer log = new StringBuffer("");
                for (String s : data) {
                    log.append(s + " / ");
                    dataSegment.add(new JsonPrimitive(s));
                }
                logger.debug("分词结果为：" + log);
            }
            // 算法的分词结果
            result.add("dataSegment", dataSegment);
            // 算法的运行时间
            result.addProperty("time", end - start);

            array.add(result);
        }

        json.addProperty("status", true);
        // 所有算法的分词结果
        json.add("result", array);
    }

    /**
     * 得到对应的分词方法的结果
     * @param method
     * @param text
     * @return
     */
    public List<String> string2dataSegment(String method, String text) {
        List<String> dataSegment = null;

        try {
            Method[] methods = wordSegment.getClass().getDeclaredMethods();
            for (Method m : methods) {
                if (m.getName().equals(method)) {
                    dataSegment = (List<String>) m.invoke(wordSegment, text);
                    break;
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return dataSegment;
    }

    public JsonObject getJson() {
        return json;
    }
}
