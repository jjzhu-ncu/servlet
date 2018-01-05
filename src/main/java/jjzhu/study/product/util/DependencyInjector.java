package jjzhu.study.product.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import jjzhu.study.product.action.ProductAction;
import jjzhu.study.product.dao.ProductDAO;
import jjzhu.study.product.dao.impl.ProductDAOImpl;
import jjzhu.study.product.model.Product;
import jjzhu.study.product.validator.ProductValidator;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

/**
 * Created by zhujiajunup on 2017/6/12.
 */
public class DependencyInjector {
    private DataSource dataSource;
    public void start(){
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("com.mysql.jdbc.Driver");
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306/test");;
            cpds.setUser("root");
            cpds.setPassword("1111");
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);
            dataSource = cpds;
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public void shutdown(){
        try {
            DataSources.destroy(dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object getObject(Class type){
        if(type == ProductValidator.class){
            return new ProductValidator();
        }else if(type == ProductDAO.class){
            return createProductDAO();
        }else if(type == ProductAction.class){
            return createProductAction();
        }
        return null;
    }

    private ProductAction createProductAction(){
        ProductAction action = new ProductAction();
        action.setProductDAO(createProductDAO());
        return action;
    }

    private ProductDAO createProductDAO(){
        ProductDAO dao = new ProductDAOImpl();
        dao.setDataSource(dataSource);
        return dao;
    }

}
