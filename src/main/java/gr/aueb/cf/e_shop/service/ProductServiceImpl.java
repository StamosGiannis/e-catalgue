package gr.aueb.cf.e_shop.service;

import gr.aueb.cf.e_shop.Model.Product;
import gr.aueb.cf.e_shop.dto.ProductInsertDTO;

import gr.aueb.cf.e_shop.dto.ProductUpdateDTO;
import gr.aueb.cf.e_shop.mapper.Mapper;
import gr.aueb.cf.e_shop.repository.ProductRepository;

import gr.aueb.cf.e_shop.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.e_shop.service.exceptions.ProductNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final ProductRepository productRepository;

    @Transactional
    @Override
    public Product insertProduct(ProductInsertDTO dto) throws Exception {
        Product product = null;

        try {
            product = productRepository.save(Mapper.mapToProduct(dto));
            if (product.getId() == null) {
                throw new Exception("insert Error");
            }
            log.info("insert success for product with id: " + product.getId());
        } catch (Exception e) {
            log.error("insert error: " + e.getMessage());
            throw e;
        }
        return product;
    }

    @Transactional
    @Override
    public Product updateProduct(ProductUpdateDTO dto) throws EntityNotFoundException {
        Product existingProduct = null;


        try {

            existingProduct = Mapper.mapToProduct(dto);
            productRepository.findById(existingProduct.getId()).orElseThrow(() ->
                    new EntityNotFoundException(Product.class,  dto.getId()));

            productRepository.save(existingProduct);
            log.info("Product with id: " + existingProduct.getId() + " was updated");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return existingProduct;
    }

    @Transactional
    @Override
    public Product deleteProduct(Long id) throws EntityNotFoundException {
        Product product;

        try {
            product = productRepository.findById(id).orElseThrow(()
            -> new EntityNotFoundException (Product.class, id));
            productRepository.deleteById(id);
            log.info("Product with id: " + id + " deleted");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return product;
    }

    @Override
    public List<Product> getProductByProductName(String productName) throws ProductNotFoundException {
        List<Product> products;

        try {
            products = productRepository.findByProductName(productName);
            if (products.isEmpty()) throw new ProductNotFoundException(productName);
            log.info("Products with product name " + productName + " were found");
        } catch (ProductNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return products;
    }

    @Override
    public List<Product> findAllProducts() throws EntityNotFoundException {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) throws EntityNotFoundException {
        Product product;

        try {
            product = productRepository.findById(id).orElseThrow(()
            -> new EntityNotFoundException(Product.class, id));
            log.info("Product with id: " + id + " was found");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return product;
    }




}
