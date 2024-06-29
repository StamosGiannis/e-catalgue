package gr.aueb.cf.e_shop.mapper;

import gr.aueb.cf.e_shop.Model.Customer;
import gr.aueb.cf.e_shop.Model.Product;
import gr.aueb.cf.e_shop.Model.User;
import gr.aueb.cf.e_shop.dto.*;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private Mapper() {}

    public static Customer extractCustomerFromRegisterCustomerDTO(RegisterCustomerDTO dto) {
        return new Customer(dto.getFirstname(), dto.getLastname(), dto.getEmail(), dto.getPhoneNumber());
    }

    public static User extractUserFromRegisterCustomerDTO(RegisterCustomerDTO dto) {
        return User.NEW_CUSTOMER(dto.getUsername(), dto.getPassword());
    }

    public static Product mapToProduct(ProductInsertDTO dto) {
        return new Product(null, dto.getProductName(), dto.getDescription(), dto.getPrice(), dto.getImageUrl(), dto.getStockQuantity());
    }

    public static Product mapToProduct(ProductUpdateDTO dto) {
        return new Product(dto.getId(), dto.getProductName(), dto.getDescription(), dto.getPrice(), dto.getImageUrl(), dto.getStockQuantity());
    }

    public static ProductReadOnlyDTO mapToProductReadOnlyDTO(Product product) {
        return new ProductReadOnlyDTO(product.getId(), product.getProductName(), product.getDescription(), product.getPrice(), product.getStockQuantity(), product.getImageUrl());
    }

    public static CustomerReadOnlyDTO mapToCustomerReadOnlyDTO(Customer customer) {
        return new CustomerReadOnlyDTO(customer.getId(), customer.getFirstname(), customer.getLastname(), customer.getEmail(), customer.getPhoneNumber(), customer.getUser().getUsername(), customer.getUser().getRole(), customer.getUser().getStatus() );
    }
}
