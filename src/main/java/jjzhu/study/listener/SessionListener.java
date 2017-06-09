package jjzhu.study.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.awt.dnd.Autoscroll;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hzzhujiajun on 2017/6/9.
 */
@WebListener(value = "session listener")
public class SessionListener implements HttpSessionListener, ServletContextListener{

    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext servletContext = session.getServletContext();
        AtomicInteger userCounter = (AtomicInteger)servletContext.getAttribute("userCounter");
        int userCount = userCounter.incrementAndGet();
        System.out.println("userCount increment:" + userCount);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext servletContext = session.getServletContext();
        AtomicInteger userCounter = (AtomicInteger)servletContext.getAttribute("userCounter");
        int userCount = userCounter.decrementAndGet();
        System.out.println("userCount decrement:" + userCount);
    }

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("userCounter", new AtomicInteger());
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
