package jjzhu.study.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhujiajunup on 2017/6/9.
 */
@WebServlet(name = "ServletConfigServlet",
urlPatterns = { "/servletConfig"},
initParams = {
        @WebInitParam(name="admin", value = "jjzhu"),
        @WebInitParam(name = "email", value = "example@xx.com")
})
public class ServletConfigServlet implements Servlet{
    private transient ServletConfig servletConfig ;
    public void init(ServletConfig config) throws ServletException {
        this.servletConfig = config;
    }

    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        String admin = this.servletConfig.getInitParameter("admin");
        String email = this.servletConfig.getInitParameter("email");
        res.setContentType("text/html");
        PrintWriter writer = res.getWriter();
        writer.print("<html><head>"+this.servletConfig.getServletName()+"\n</head>" +
                "<body>admin:"+admin+"\nemail:"+email + "</body></html>");
    }

    public String getServletInfo() {
        return "ServletConfig Demo";
    }

    public void destroy() {

    }
}
