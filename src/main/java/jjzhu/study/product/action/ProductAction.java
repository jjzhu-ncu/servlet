package jjzhu.study.product.action;

import jjzhu.study.product.dao.DAOException;
import jjzhu.study.product.dao.ProductDAO;
import jjzhu.study.product.model.Product;

import java.util.List;

/**
 * Created by hzzhujiajun on 2017/6/12.
 */
public class ProductAction {
    private ProductDAO productDAO;

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void save(Product product){
        try {
            productDAO.insertProduct(product);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getProducts(){
        try {
            return productDAO.getProducts();
        } catch (DAOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
