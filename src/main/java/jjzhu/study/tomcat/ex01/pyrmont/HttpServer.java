package jjzhu.study.tomcat.ex01.pyrmont;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by zhujiajunup on 2017/6/22.
 */
public class HttpServer {

    public static final String WEB_ROOT = "E:\\codingspace\\java\\servlet\\src\\main" + File.separator + "webapp\\";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public void await() throws Exception {
        ServerSocket serverSocket = null;
        int port = 8081;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        if (serverSocket == null) {
            throw new Exception("server socket error");
        }

        while (!shutdown) {
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            socket = serverSocket.accept();

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            Request request = new Request(inputStream);
            request.parse();
            Response response = new Response(outputStream);
            response.setRequest(request);
            response.sendStaticResource();
            socket.close();


        }
    }

    public static void main(String[] args) throws Exception {
        HttpServer server = new HttpServer();
        server.await();
    }
}
