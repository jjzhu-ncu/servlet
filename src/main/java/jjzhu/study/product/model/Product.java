package jjzhu.study.product.model;

import java.io.Serializable;

/**
 * Created by hzzhujiajun on 2017/6/12.
 */
public class Product implements Serializable{
    private String name;
    private String description;
    private float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return String.format("{name:%s\tdescription:%s\tprice:%f}", name, description, price);
    }
}
