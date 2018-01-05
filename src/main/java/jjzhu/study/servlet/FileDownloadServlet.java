package jjzhu.study.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zhujiajunup on 2017/6/13.
 */
@WebServlet(urlPatterns = { "/download" })
public class FileDownloadServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session == null || session.getAttribute("loggedIn") == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
        }
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/download");
        File file = new File(dataDirectory, "secret.pdf");
        if(file.exists()){
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=secret.pdf");;
            byte[] buffer = new byte[1024];
            try(FileInputStream fis = new FileInputStream(file)){
                try(BufferedInputStream bis = new BufferedInputStream(fis)){
                    OutputStream os = response.getOutputStream();
                    int i ;
                    while((i = bis.read(buffer)) != -1){
                        os.write(buffer, 0, i);
                    }
                }
            }

        }
    }
}
