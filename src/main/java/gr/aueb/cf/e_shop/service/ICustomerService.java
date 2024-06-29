package gr.aueb.cf.e_shop.service;

import gr.aueb.cf.e_shop.Model.Customer;
import gr.aueb.cf.e_shop.dto.RegisterCustomerDTO;
import gr.aueb.cf.e_shop.service.exceptions.CustomerAlreadyExistsException;
import gr.aueb.cf.e_shop.service.exceptions.CustomerNotFoundException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface ICustomerService {
    Customer registerCustomer(RegisterCustomerDTO dto) throws CustomerAlreadyExistsException;
    List<Customer> findAllCustomers() throws Exception;
    List<Customer> findByLastname(String lastname) throws CustomerNotFoundException;
}
