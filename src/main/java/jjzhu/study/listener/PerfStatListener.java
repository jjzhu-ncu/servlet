package jjzhu.study.listener;

import com.sun.net.httpserver.HttpServer;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhujiajunup on 2017/6/9.
 */
@WebListener
public class PerfStatListener implements ServletRequestListener{

    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest request = sre.getServletRequest();
        request.setAttribute("start", System.nanoTime());
    }
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequest request = sre.getServletRequest();
        Long start = (Long) request.getAttribute("start");
        Long end = System.nanoTime();
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();
        System.out.println(uri + ":" + (end - start)/1000);
    }

}
