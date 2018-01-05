package jjzhu.study.product.dao.impl;

import jjzhu.study.product.dao.BaseDAO;
import jjzhu.study.product.dao.DAOException;
import jjzhu.study.product.dao.ProductDAO;
import jjzhu.study.product.model.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhujiajunup on 2017/6/12.
 */
public class ProductDAOImpl extends BaseDAO implements ProductDAO {
    private static final String GET_PRODUCTS_SQL = "SELECT name, description, price from products";
    private static final String INSERT_PRODUCT_SQL = "" +
            "INSERT INTO products (name, description, price) values (?,?,?)";

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Product> getProducts() throws DAOException {
        List<Product> products = new ArrayList<>();
        try {
            try(Connection connection = getConnection()){
                try(PreparedStatement statement = connection.prepareStatement(GET_PRODUCTS_SQL)){
                    try(ResultSet resultSet = statement.executeQuery()){
                        while(resultSet.next()){
                            Product product = new Product();
                            product.setName(resultSet.getString("name"));
                            product.setDescription(resultSet.getString("description"));
                            product.setPrice(resultSet.getFloat("price"));
                            products.add(product);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        return products;
    }

    @Override
    public boolean insertProduct(Product product) throws DAOException {
        try {
            try(Connection connection = getConnection()){
                try(PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT_SQL)){
                    statement.setString(1, product.getName());
                    statement.setString(2, product.getDescription());
                    statement.setFloat(3, product.getPrice());
                    statement.execute();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        return false;
    }
}
