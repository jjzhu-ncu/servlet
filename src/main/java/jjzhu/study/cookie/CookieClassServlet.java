package jjzhu.study.cookie;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhujiajunup on 2017/6/9.
 */
@WebServlet(name = "CookieClassServlet", urlPatterns = { "/cookieClass" })
public class CookieClassServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>cookie class</title></head>");
        writer.println("<body>");
        if(cookies != null){
            for(Cookie cookie: cookies){
                writer.println(cookie.getName()+":"+cookie.getValue()+"</br>");
            }
        }
        writer.println("</body></html>");
    }
}
