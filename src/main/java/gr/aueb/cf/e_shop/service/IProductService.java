package gr.aueb.cf.e_shop.service;

import gr.aueb.cf.e_shop.Model.Product;
import gr.aueb.cf.e_shop.dto.ProductInsertDTO;
import gr.aueb.cf.e_shop.dto.ProductReadOnlyDTO;
import gr.aueb.cf.e_shop.dto.ProductUpdateDTO;
import gr.aueb.cf.e_shop.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.e_shop.service.exceptions.ProductNotFoundException;



import java.util.List;

public interface IProductService {
    Product insertProduct(ProductInsertDTO dto) throws Exception;
    Product updateProduct(ProductUpdateDTO dto) throws EntityNotFoundException;
    Product deleteProduct(Long id) throws EntityNotFoundException;
    List<Product> getProductByProductName(String productName) throws ProductNotFoundException;
    Product getProductById(Long id) throws EntityNotFoundException;
    List<Product> findAllProducts() throws EntityNotFoundException;

}
