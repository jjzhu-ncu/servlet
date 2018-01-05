package jjzhu.study.hiddenfields;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhujiajunup on 2017/6/9.
 */
@WebServlet(name = "CustomerServlet", urlPatterns = {
        "/customer", "/editCustomer", "/updateCustomer"
})
public class CustomerServlet extends HttpServlet {
    private List<Customer> customers = new ArrayList<Customer>();

    @Override
    public void init(){
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setCity("london");
        customer1.setName("jjzhu");
        Customer customer2 = new Customer();

        customer2.setId(2);
        customer2.setCity("paris");
        customer2.setName("cczhang");
        customers.add(customer1);
        customers.add(customer2);
    }

    private void sendCustomerList( HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>Customers</title><body>" +
                "<h2>Customers</h2></br" +
                "<ul>");
        for(Customer c: customers){
            writer.println("<li>" + c.getName()+"("+c.getCity()+")("+"<a href='editCustomer?id="
            + c.getId() + "')>edit</a>");
        }
        writer.println(
                "</ul>" +
                "</body></html>");
    }

    private Customer getCustomer(int id){
        for(Customer c: customers){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }

    private void sendEditCustomerForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        int id = 0;
        try{
            id = Integer.parseInt(request.getParameter("id"));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        Customer customer = getCustomer(id);
        if(customer != null){
            writer.println("<html><head><title>Edit customer</title></head>");
            writer.println("<body><h2>edit customer</h2>");
            writer.println("<form method='post' action='updateCustomer'>");
            writer.println("<input type='hidden' name='id' value='"+customer.getId()+"'/>");
            writer.println("<input name='name' value='"+customer.getName().replaceAll("'", "&#39;")+"'/><br/>");
            writer.println("<input name='city' value='"+customer.getCity().replaceAll("'", "&#39;")+"'/><br/>");
            writer.println("<input type='submit' value='Update'/>");
            writer.println("</br><a href='customer'> Customer list </a>");
            writer.println("</body></html>");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        if(uri.endsWith("/customer")){
            sendCustomerList(response);
        }else{
            sendEditCustomerForm(request, response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = 0;
        try{
            id = Integer.parseInt(request.getParameter("id"));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        Customer customer = getCustomer(id);
        if(customer != null){
            customer.setName(request.getParameter("name"));
            customer.setCity(request.getParameter("city"));
        }
        sendCustomerList(response);
    }
}
