package jjzhu.study.tomcat.ex02.pyrmont;

import java.io.IOException;

/**
 * Created by zhujiajunup on 2017/6/23.
 */
public class StaticResourceProcessor {
    public void process(Request request, Response response){
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
