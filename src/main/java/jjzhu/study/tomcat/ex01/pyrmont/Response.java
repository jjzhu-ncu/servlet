package jjzhu.study.tomcat.ex01.pyrmont;

import com.sun.org.apache.regexp.internal.RE;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zhujiajunup on 2017/6/22.
 */
public class Response {
    private static final int BUFFER_SIZE = 1024;
    private OutputStream outputStream;
    private Request request;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        if(request.getUri() == null){
            return ;
        }
        System.out.print(request.getUri().substring(1));
        File file = new File(HttpServer.WEB_ROOT, request.getUri().substring(1));
        System.out.println(file.getAbsolutePath());
        String h = "HTTP/1.1 200\r\n" +
                "Content-Type: text/html\r\n" ;
        StringBuffer sb = new StringBuffer();
        try (FileInputStream fis = new FileInputStream(file)) {
            int ch;
            while((ch = fis.read(bytes, 0, BUFFER_SIZE)) != -1){
                for(int i = 0; i < ch; i ++){
                    sb.append((char)bytes[i]);
                    System.out.print((char)bytes[i]);
                }
            }
            h += "Content-Length:" + sb.length() + "\r\n" +
                "\r\n" + sb.toString();
            outputStream.write(h.getBytes());
        } catch (FileNotFoundException e) {
            String html = "<h1>File Not Found<h1>";
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length:" + html.length() + "\r\n" +
                    "\r\n" + html;
            //ignore
            outputStream.write(errorMessage.getBytes());
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }


}
