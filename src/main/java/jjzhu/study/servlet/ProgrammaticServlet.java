package jjzhu.study.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by hzzhujiajun on 2017/6/14.
 */
@WebServlet(urlPatterns = { "/prog" })
public class ProgrammaticServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        if(request.authenticate(response)){

            writer.println("welcome");
        }else{
            writer.println("User not authenticated");
        }
    }

}
