package jjzhu.study.product.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zhujiajunup on 2017/6/12.
 */
public class BaseDAO implements DAO{
    protected DataSource dataSource;

    @Override
    public Connection getConnection() throws DAOException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
    }
}
