package jjzhu.study.servlet;

import jjzhu.study.util.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by hzzhujiajun on 2017/6/12.
 */
@WebServlet(urlPatterns = {"/singleUpload"})
@MultipartConfig
public class SingleUploadServlet extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part part = request.getPart("filename");
        String filename = HttpUtils.getFileName(part);
        if(filename != null && !filename.isEmpty()){
            part.write(getServletContext().getRealPath("/WEB-INF")+"/"+filename);
        }
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<br/>Uploaded file: "+filename);
        writer.println("Size:" + part.getSize());
        writer.println("Author:"+request.getParameter("author"));
    }
}
