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
@WebServlet(name = "PreferenceServlet", urlPatterns = { "/preference"})
public class PreferenceServlet extends HttpServlet{
    public static final String MENU = "<div>" +
            "<a href='cookieClass'>Cookie class</a></br>" +
            "<a href='cookieInfo'>Cookie Info</a></br>" +
            "<a href='preference'>Preference</a></br>" +
            "</div>";
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.print("<html><head><title>Preference</title></head><body>" +
                MENU+"<form method='post'>" +
                "Font Size" +
                "<select name='titleFontSize'>" +
                "<option>large</option>" +
                "<option>x-large</option>" +
                "</select></br>" +
                "title style:" +
                "<select name='titleStyle' multiple>" +
                "<option>italic</option>" +
                "<option>bold</option>" +
                "</select>" +
                "<input type='submit' value='Set'/>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String [] titleStyle = request.getParameterValues("titleStyle");
        String titleFontSize = request.getParameter("titleFontSize");
        response.addCookie(new Cookie("titleFontSize", titleFontSize));
        Cookie cookie = new Cookie("titleFontWight", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        cookie = new Cookie("titleFontStyle", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        if(titleStyle != null){
            for(String style: titleStyle){
                if(style.equals("bold")){
                    response.addCookie(new Cookie("titleFontWeight", "bold"));
                }else if(style.equals("italic")){
                    response.addCookie(new Cookie("titleFontStyle", "italic"));
                }
            }
        }
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><tille>ok</title></head><body>OK</body></html>");

    }
}
