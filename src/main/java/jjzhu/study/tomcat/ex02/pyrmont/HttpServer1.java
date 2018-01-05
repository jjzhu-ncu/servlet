package jjzhu.study.tomcat.ex02.pyrmont;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhujiajunup on 2017/6/23.
 */
public class HttpServer1 {
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public void await(){
        int port = 8081;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
            while(!shutdown){
                Socket socket = null;
                InputStream input = null;
                OutputStream out = null;
                socket = serverSocket.accept();
                input = socket.getInputStream();
                out = socket.getOutputStream();
                Request request = new Request(input);
                request.parse();
                if(request.getUri() == null){
                    continue;
                }
                Response response = new Response(out);
                response.setRequest(request);
                if(request.getUri().startsWith("/servlet/")){
                    ServletProcessor1 processor = new ServletProcessor1();
                    processor.process(request, response);
                }else{
                    StaticResourceProcessor resourceProcessor = new StaticResourceProcessor();
                    resourceProcessor.process(request, response);
                }
                socket.close();
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            }
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }

    }

    public static void main(String [] args){
        HttpServer1 server = new HttpServer1();
        server.await();
    }
}
