package jjzhu.study.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhujiajunup on 2017/6/9.
 */
public class MyGenericServlet extends GenericServlet{
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        String admin = this.getInitParameter("admin");
        String email = this.getInitParameter("email");
        res.setContentType("text/html");
        PrintWriter writer = res.getWriter();
        writer.print("<html>\n<head>"+this.getServletName()+"\n</head>\n" +
                "<body>admin:"+admin+"\nemail:"+email + "</body>\n</html>");
    }
}
