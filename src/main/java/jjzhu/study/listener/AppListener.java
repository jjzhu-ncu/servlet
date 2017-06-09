package jjzhu.study.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hzzhujiajun on 2017/6/9.
 */
@WebListener
public class AppListener implements ServletContextListener{
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        Map<String, String> contries = new HashMap<String, String>();
        contries.put("ca", "Canada");
        contries.put("cn", "China");
        servletContext.setAttribute("countries", contries);

    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
