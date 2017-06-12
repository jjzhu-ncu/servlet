package jjzhu.study.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by hzzhujiajun on 2017/6/12.
 */
@WebFilter(filterName = "LoggingFilter", urlPatterns = { "/*"},
initParams = {
        @WebInitParam(name = "logFileName", value = "log.txt"),
        @WebInitParam(name = "prefix", value = "URI")
})
public class LoggingFilter implements Filter {
    private PrintWriter logger;
    private String prefix;
    public void init(FilterConfig filterConfig) throws ServletException {
        prefix = filterConfig.getInitParameter("prefix");
        String logFileName = filterConfig.getInitParameter("logFileName");
        String appPath = filterConfig.getServletContext().getRealPath("/");
        System.out.println("logFileName:"+appPath + logFileName);

        try {
            logger = new PrintWriter(new File(appPath, logFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        logger.println(new Date() + " " + prefix + httpServletRequest.getRequestURI());
        chain.doFilter(request, response);
    }

    public void destroy() {
        if(logger != null){
            logger.close();
        }
    }
}
