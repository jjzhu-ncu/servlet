package jjzhu.study.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hzzhujiajun on 2017/6/13.
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username != null && username.equals("ken")
                && password != null && password.equals("secret")){
            HttpSession session = request.getSession(true);
            session.setAttribute("loggedIn", Boolean.TRUE);
            response.sendRedirect("download");
            return;
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
