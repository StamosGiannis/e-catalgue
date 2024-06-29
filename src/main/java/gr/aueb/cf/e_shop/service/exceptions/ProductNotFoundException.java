package gr.aueb.cf.e_shop.service.exceptions;

import gr.aueb.cf.e_shop.Model.Product;

public class ProductNotFoundException extends Exception{
    private static final long serialVersionUIOD = 1L;

    public ProductNotFoundException(String productName) {
        super("Product with productName " + productName + " was not found");
    }
}
