package jjzhu.study.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by hzzhujiajun on 2017/6/9.
 */
@WebServlet(name = "MyServlet", urlPatterns = { "/my" })
public class MyServlet implements Servlet {
    private transient ServletConfig servletConfig;
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String servletNmae = this.servletConfig.getServletName();
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.print(
                        "<html>\n" +
                        "<head>\n" +
                        "<body>hello from " + servletNmae +"\n</body>\n"+
                        "</head>\n" +
                        "</html>\n" );
    }

    public String getServletInfo() {
        return "MyServlet";
    }

    public void destroy() {

    }
}
