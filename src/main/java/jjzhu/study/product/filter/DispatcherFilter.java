package jjzhu.study.product.filter;

import jjzhu.study.product.action.ProductAction;
import jjzhu.study.product.form.ProductForm;
import jjzhu.study.product.model.Product;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by zhujiajunup on 2017/6/12.
 */
//@WebFilter(filterName = "DispatcherFilter", urlPatterns = { "/*" })
public class DispatcherFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);
        if(action.equals("product_save")){
            ProductForm productForm = new ProductForm();
            productForm.setDescription(request.getParameter("description"));
            productForm.setName(request.getParameter("name"));
            productForm.setPrice(request.getParameter("price"));
            Product product = new Product();
            product.setName(productForm.getName());;
            product.setDescription(productForm.getDescription());
            product.setPrice(Float.parseFloat(productForm.getPrice()));
            ProductAction saveProductAction = new ProductAction();
            saveProductAction.save(product);
            // save
            request.setAttribute("product", product);
        }
        String dispatchUrl = null;
        if(action.equals("product_input")){
            dispatchUrl = "/jsp/product/ProductForm.jsp";
        }else if(action.equals("product_save")){
            dispatchUrl = "/jsp/product/ProductDetails.jsp";
        }
        if(dispatchUrl != null){
            RequestDispatcher dispatcher = request.getRequestDispatcher(dispatchUrl);
            dispatcher.forward(request, response);
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
