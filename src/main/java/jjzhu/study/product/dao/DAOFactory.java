package jjzhu.study.product.dao;

import jjzhu.study.product.dao.impl.ProductDAOImpl;

/**
 * Created by hzzhujiajun on 2017/6/12.
 */
public class DAOFactory {
    public static ProductDAO getProductDAO(){
        return new ProductDAOImpl();
    }
}

