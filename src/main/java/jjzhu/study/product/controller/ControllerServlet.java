package jjzhu.study.product.controller;

import jjzhu.study.product.action.ProductAction;
import jjzhu.study.product.form.ProductForm;
import jjzhu.study.product.model.Product;
import jjzhu.study.product.util.DependencyInjector;
import jjzhu.study.product.validator.ProductValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by hzzhujiajun on 2017/6/12.
 */
@WebServlet(name = "ControllerServlet", urlPatterns = {
        "/product_input", "/product_save", "/product_list"
})
public class ControllerServlet extends HttpServlet{
    private DependencyInjector dependencyInjector;
    @Override
    public void init(){
        dependencyInjector = new DependencyInjector();
        dependencyInjector.start();
    }
    @Override
    public void destroy(){
        dependencyInjector.shutdown();
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);
        String dispatchUrl = null;
        if(action.equals("product_input")) {
            dispatchUrl = "/jsp/product/ProductForm.jsp";
        }else if(action.equals("product_list")){
            ProductAction productAction = (ProductAction) dependencyInjector.getObject(ProductAction.class);
            List<Product> products = productAction.getProducts();
            dispatchUrl = "/jsp/product/ProductList.jsp";
            request.setAttribute("products", products);
        }else if(action.equals("product_save")){
            dispatchUrl = "/jsp/product/ProductDetails.jsp";
            ProductForm productForm = new ProductForm();
            productForm.setName(request.getParameter("name"));
            productForm.setDescription(request.getParameter("description"));
            productForm.setPrice(request.getParameter("price"));

            List<String> errors = ProductValidator.validate(productForm);
            if(errors.isEmpty()) {
                Product product = new Product();
                product.setName(productForm.getName());
                product.setDescription(productForm.getDescription());
                product.setPrice(Float.parseFloat(productForm.getPrice()));
                ProductAction productAction = (ProductAction) dependencyInjector.getObject(ProductAction.class);
                productAction.save(product);
                request.setAttribute("product", product);
            }else{
                request.setAttribute("errors", errors);
                request.setAttribute("form", productForm);
                dispatchUrl = "/jsp/product/ProductForm.jsp";
            }
        }
        if(dispatchUrl != null){
            RequestDispatcher dispatcher = request.getRequestDispatcher(dispatchUrl);
            dispatcher.forward(request, response);
        }
    }
}
