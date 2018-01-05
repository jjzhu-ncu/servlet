package jjzhu.study.tomcat.ex01.pyrmont;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhujiajunup on 2017/6/22.
 */
public class Request {
    private InputStream inputStream;
    private String uri;
    public Request(InputStream input){
        this.inputStream = input;
    }

    public void parse() throws IOException {
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        i = this.inputStream.read(buffer);
        for(int j = 0; j < i; j ++){
            request.append((char)buffer[j]);
        }
        System.out.println(request.toString());
        this.uri = this.parseUri(request.toString());
    }

    private String parseUri(String reqStr){
        int index1, index2;
        index1 = reqStr.indexOf(' ');
        if(index1 != -1){
            index2 = reqStr.indexOf(' ', index1 + 1);
            if(index2 > index1){
                return reqStr.substring(index1 + 1, index2 + 1);
            }
        }
        return null;
    }

    public String getUri(){
        return this.uri;
    }

}
