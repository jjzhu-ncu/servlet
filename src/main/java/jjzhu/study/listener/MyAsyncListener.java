package jjzhu.study.listener;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;

/**
 * Created by zhujiajunup on 2017/6/14.
 */
public class MyAsyncListener implements AsyncListener{
    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("Complete");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("timeout");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        System.out.println("error");
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("start async");
    }
}
