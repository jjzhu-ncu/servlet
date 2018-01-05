package jjzhu.study.tomcat.ex02.pyrmont;

import jjzhu.study.tomcat.ex01.pyrmont.HttpServer;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * Created by zhujiajunup on 2017/6/22.
 */
public class Response implements ServletResponse {
    private static final int BUFFER_SIZE = 1024;
    private OutputStream outputStream;
    private Request request;
    PrintWriter writer;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        System.out.print(request.getUri().substring(1));
        File file = new File(HttpServer.WEB_ROOT, request.getUri().substring(1));
        if (file.exists()) {
            String html = "<h1>File Not Found<h1>";
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length:" + html.length() + "\r\n" +
                    "\r\n" + html;
            //ignore
            outputStream.write(errorMessage.getBytes());
        }
        System.out.println(file.getAbsolutePath());
        String h = "HTTP/1.1 200\r\n" +
                "Content-Type: text/html\r\n";
        StringBuffer sb = new StringBuffer();
        FileInputStream fis = new FileInputStream(file);
        int ch;
        while ((ch = fis.read(bytes, 0, BUFFER_SIZE)) != -1) {
            for (int i = 0; i < ch; i++) {
                System.out.print((char) bytes[i]);
            }
        }
        h += "Content-Length:" + sb.length() + "\r\n" +
                "\r\n" + sb.toString();
        outputStream.write(h.getBytes());


    }

    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        writer = new PrintWriter(outputStream, true);
        return writer;
    }

    public void setCharacterEncoding(String s) {

    }

    public void setContentLength(int i) {

    }

    public void setContentType(String s) {

    }

    public void setBufferSize(int i) {

    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() throws IOException {

    }

    public void resetBuffer() {

    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {

    }

    public void setLocale(Locale locale) {

    }

    public Locale getLocale() {
        return null;
    }
}
