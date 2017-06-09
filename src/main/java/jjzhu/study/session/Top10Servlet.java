package jjzhu.study.session;

import javax.naming.directory.SearchResult;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzzhujiajun on 2017/6/9.
 */
@WebServlet(name = "top10Servlet", urlPatterns = { "/top10" } )
public class Top10Servlet extends HttpServlet{
    private List<String> london ;
    private List<String> paris;
    @Override
    public void init(){
        london = new ArrayList<String>(10);
        london.add("London 1");
        london.add("London 2");
        london.add("London 3");
        london.add("London 4");
        london.add("London 5");
        london.add("London 6");
        london.add("London 7");
        london.add("London 8");
        london.add("London 9");
        london.add("London 10");
        paris = new ArrayList<String>(10);
        paris.add("paris 1");
        paris.add("paris 2");
        paris.add("paris 3");
        paris.add("paris 4");
        paris.add("paris 5");
        paris.add("paris 6");
        paris.add("paris 7");
        paris.add("paris 8");
        paris.add("paris 9");
        paris.add("paris 10");
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String city = request.getParameter("city");
        if(city != null && (city.equals("london") || city.equals("paris"))){
            showAttractions(request, response, city);
        }else{
            showMainPage(request, response);
        }
    }
    private void showMainPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.print("<html><head></head><body>" +
                "<a href='?city=london'>london</a>" +
                "<br/>" +
                "<a href='?city=paris'>paris</a>" +
                "</body></html>");
    }
    private void showAttractions(HttpServletRequest request, HttpServletResponse response, String city) throws IOException {
        int page = 1;
        String pageParameter = request.getParameter("page");
        if(pageParameter != null){
            try {
                page = Integer.parseInt(pageParameter);
            }catch (NumberFormatException e){
                response.getWriter().println(e.getMessage());
            }
            if(page > 2){
                page = 1;
            }
        }
        List<String> att = null;
        if(city.equals("london")){
            att = london;
        }else{
            att = paris;
        }
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head>" +
                "</head><body>");
        writer.println("<a href='top10'>select city</a>");
        writer.println("<hr/>Page " + page + "<hr/>");
        int start = page * 5 - 5;
        for(int i = start; i < start + 5; i ++){
            writer.println(att.get(i)+"<br/>");
        }
        writer.print("<hr style='color:blue'/>" +
                "<a href='?city=" + city+"&page=1'>" + page+"</a>");
        writer.print(
                "<a href='?city=" + city+"&page=2'>" + page+"</a>");
        writer.println("</body></html>");
    }
}
