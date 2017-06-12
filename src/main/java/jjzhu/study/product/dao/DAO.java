package jjzhu.study.product.dao;

import java.sql.Connection;

/**
 * Created by hzzhujiajun on 2017/6/12.
 */
public interface DAO {
    Connection getConnection() throws Exception;
}
