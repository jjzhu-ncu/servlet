package jjzhu.study.servlet;

import jjzhu.study.listener.MyAsyncListener;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhujiajunup on 2017/6/14.
 */
@WebServlet(name = "AsyncDispatchServlet", urlPatterns = { "/asyncDispatch" },
asyncSupported = true)
public class AsyncDispatchServlet extends HttpServlet{
    public void doGet(final HttpServletRequest request, HttpServletResponse response){
        final AsyncContext asyncContext = request.startAsync();
        request.setAttribute("mainThread", Thread.currentThread().getName());
        asyncContext.setTimeout(5000);
        asyncContext.addListener(new MyAsyncListener());
        asyncContext.start(() -> {
           try{
               Thread.sleep(3000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           request.setAttribute("workerThread", Thread.currentThread().getName());
           asyncContext.dispatch("/jsp/threadNames.jsp");
        });

    }
}
