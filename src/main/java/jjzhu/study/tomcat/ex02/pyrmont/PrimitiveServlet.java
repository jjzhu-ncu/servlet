package jjzhu.study.tomcat.ex02.pyrmont;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhujiajunup on 2017/6/23.
 */
public class PrimitiveServlet implements Servlet {
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("PrimitiveServlet init");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("from service");
        PrintWriter writer = servletResponse.getWriter();
        writer.println("hello. roses are red");
        writer.println("violets are blue");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("destroy");
    }
}
