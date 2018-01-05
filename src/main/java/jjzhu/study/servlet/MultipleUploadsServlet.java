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
import java.util.Collection;

/**
 * Created by zhujiajunup on 2017/6/13.
 */
@WebServlet(urlPatterns = {"/multipleUploads"})
@MultipartConfig
public class MultipleUploadsServlet extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        Collection<Part> parts = request.getParts();
        for(Part part: parts){
            if(part.getContentType() != null){
                String filename = HttpUtils.getFileName(part);
                if(filename != null && !filename.isEmpty()){
                    part.write(getServletContext().getRealPath("/WEB-INF")+"/upload"+filename);
                    writer.println("Name:" + filename+"<br/>");
                    writer.println("Size:" + part.getSize()+"<br/>");
                }
            }else {
                String partName = part.getName();
                String fieldValue = request.getParameter(partName);
                writer.println(partName+":"+fieldValue+"<br/>");
            }
        }

    }
}
