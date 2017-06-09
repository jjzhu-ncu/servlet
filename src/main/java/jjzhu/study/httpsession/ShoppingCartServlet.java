package jjzhu.study.httpsession;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by hzzhujiajun on 2017/6/9.
 */
@WebServlet(name = "ShoppingCartServlet", urlPatterns = {
        "/products", "/viewProductDetails",
        "/addToCart", "/viewCart"
})
public class ShoppingCartServlet extends HttpServlet {
    private static final String CART_ATTRIBUTE = "CART";
    private List<Product> products = new ArrayList<Product>(10);
    private NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);

    public void init(){
        products.add(new Product(1, "Jopper", "one piece", 10.9F));
        products.add(new Product(2, "Tomorrow", "a book for child", 24.9F));
        products.add(new Product(3, "Red and Black", "Sti c", 139.1F));
        products.add(new Product(4, "Give me", "12321", 14.23F));

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        if(uri.endsWith("/products")){
            sendProductList(response);
        }else if(uri.endsWith("/viewProductDetails")){
            sendProductDetails(request, response);
        }else if(uri.endsWith("viewCart")){
            showCart(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = 0;
        int quantity = 0;
        try{
            id = Integer.parseInt(request.getParameter("id"));
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        Product product = getProduct(id);
        if(product != null && quantity >= 0){
            ShoppingItem item = new ShoppingItem(product, quantity);
            HttpSession session = request.getSession();
            List<ShoppingItem> cart = (List<ShoppingItem>) session.getAttribute(CART_ATTRIBUTE);
            if(cart == null){
                cart = new ArrayList<ShoppingItem>();
                session.setAttribute(CART_ATTRIBUTE, cart);
            }
            cart.add(item);
        }
        sendProductList(response);
    }

    private void sendProductList(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><tille>Products</title></head><body><ul>");
        for(Product product: products){
            writer.println("<li>"+product.getName()+"("+format.format(product.getPrice())+")(" +
                    "<a href='viewProductDetails?id="+product.getId()+"'>details</a>)");
        }
        writer.println("</ul>");
        writer.println("<a href='viewCart'>view cart</a>");
        writer.println("</body></html>");
    }

    private Product getProduct(int id){
        for(Product p: products){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    private void sendProductDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        int id = 0;
        try{
            id = Integer.parseInt(request.getParameter("id"));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        Product product = getProduct(id);
        if(product != null){
            writer.println("<html><head><tille>Product detail</title></head><body>");
            writer.println("<form method='post' action='addToCart'>");
            writer.println("<input type='hidden' name='id' value='"+product.getId()+"'/>");
            writer.println("<table>");
            writer.println("<tr><td>Name:</td><td>"+product.getName()+"</td></tr>");
            writer.println("<tr><td>Description:</td><td>"+product.getDescription()+"</td></tr>");
            writer.println(
                    "<tr><td><input name='quantity'/></td>" +
                    "<td><input type='submit' value='Buy'/></td>" +
                    "</tr>" +
                    "<tr><td><a href='products'>product list</a></td></tr>"
            );
            writer.println("</table>");
            writer.println("</form></body></html>");
        }
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>Shopping Cart</title></head><body>");
        writer.println("<a href='products'> product list </a>");
        HttpSession session = request.getSession();
        List<ShoppingItem> cart = (List<ShoppingItem>) session.getAttribute(CART_ATTRIBUTE);
        if(cart != null){
            writer.println("<table>");
            writer.println("<tr><td>Quantity</td>" +
                    "<td>Product</td>" +
                    "<td>price</td>" +
                    "<td>amount</td></tr>");
            double total = 0.0D;
            for(ShoppingItem item : cart){
                Product product = item.getProduct();
                int quantity = item.getQuantity();
                if(quantity != 0){
                    float price = product.getPrice();
                    writer.println("<tr>");
                    writer.println("<td>"+quantity+"</td>");
                    writer.println("<td>"+product.getName()+"</td>");
                    writer.println("<td>" + format.format(price)+"</td>");
                    double subtotal = price * quantity;
                    writer.println("<td>"+format.format(subtotal)+"</td>");
                    total += subtotal;
                    writer.println("</tr>");
                }
            }
            writer.println("<tr><td>total:"+format.format(total)+"</td></tr>");
            writer.println("</table");
        }
        writer.println("</body></html>");

    }
}
