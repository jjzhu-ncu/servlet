package jjzhu.study.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhujiajunup on 2017/6/9.
 */
@WebServlet( name = "FormServlet", urlPatterns = { "/form"})
public class FormServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>TITLE</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<form method='POST'>");
        writer.println("name:<input name='name'/>");
        writer.println("<input type='submit'/>");
        writer.println("</form>");
        writer.println("</body>");
        writer.println("</html>");
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>POST</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("Name:"+req.getParameter("name"));
        writer.println("</body>");
        writer.println("</html>");
    }
}
