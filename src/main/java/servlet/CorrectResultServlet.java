package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 得到正确的分词结果，然后存储到文件中
 */
@WebServlet("/CorrectResultServlet")
public class CorrectResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String text = request.getParameter("text");
        String[] array = request.getParameterValues("correct[]");

        System.out.println("text:" + text);
        System.out.print("correct: ");
        for (String string : array) {
            System.out.print(string + " ");
        }
        System.out.println();
//        System.out.println(array.toString());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("使用 doGet 方法");
        doPost(request, response);
    }
}
