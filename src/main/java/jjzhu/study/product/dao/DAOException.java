package jjzhu.study.product.dao;

/**
 * Created by hzzhujiajun on 2017/6/12.
 */
public class DAOException extends Exception{
    private String message;
    public DAOException(){
    }
    public DAOException(String message){
       this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
