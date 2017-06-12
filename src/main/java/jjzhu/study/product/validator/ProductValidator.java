package jjzhu.study.product.validator;

import jjzhu.study.product.form.ProductForm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzzhujiajun on 2017/6/12.
 */
public class ProductValidator {
    public static List<String> validate(ProductForm productForm){
        List<String> errors = new ArrayList<>();
        String name = productForm.getName();
        if(name == null || name.trim().isEmpty()){
            errors.add("Product must have a name");
        }
        String price = productForm.getPrice();
        if(price == null || price.trim().isEmpty()){
            errors.add("product must have a price");
        }else{
            try{
                Float.parseFloat(price);
            }catch (NumberFormatException e){
                errors.add("Invalid price value "+price);
            }
        }
        return errors;
    }
}
