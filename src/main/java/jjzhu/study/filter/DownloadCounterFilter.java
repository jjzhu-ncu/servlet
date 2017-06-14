package jjzhu.study.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hzzhujiajun on 2017/6/12.
 */
/*@WebFilter(filterName = "DownloadCounterFilter", urlPatterns = {"*//*"})*/
public class DownloadCounterFilter implements Filter{
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Properties downLoadLog ;
    File logFile;
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Download counter filter ");
        String appPath = filterConfig.getServletContext().getRealPath("/");
        logFile = new File(appPath, "downloadLog.txt");
        if(!logFile.exists()){
            try {
                boolean result = logFile.createNewFile();
                if(!result){
                    throw new Exception(logFile.getName() + " create failed");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        downLoadLog = new Properties();
        try {
            downLoadLog.load(new FileReader(logFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final String uri = httpServletRequest.getRequestURI();
        executorService.execute(() -> {
                String property = downLoadLog.getProperty(uri);
                if(property == null){
                    downLoadLog.setProperty(uri, "1");
                }else{
                    int count = Integer.parseInt(property);
                    count++;
                    downLoadLog.setProperty(uri, Integer.toString(count));
                }

                try {
                    downLoadLog.store(new FileWriter(logFile), "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        );
        chain.doFilter(request, response);
    }

    public void destroy() {
        executorService.shutdown();
    }
}
