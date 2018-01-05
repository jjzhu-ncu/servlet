package jjzhu.study.product.dao;



import jjzhu.study.product.model.Product;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by zhujiajunup on 2017/6/12.
 */
public interface ProductDAO {
    void setDataSource(DataSource dataSource);

    List<Product> getProducts() throws DAOException;

    boolean insertProduct(Product product) throws DAOException;
}
