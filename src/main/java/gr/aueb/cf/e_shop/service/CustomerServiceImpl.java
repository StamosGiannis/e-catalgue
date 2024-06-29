package gr.aueb.cf.e_shop.service;

import gr.aueb.cf.e_shop.Model.Customer;
import gr.aueb.cf.e_shop.Model.User;
import gr.aueb.cf.e_shop.dto.RegisterCustomerDTO;
import gr.aueb.cf.e_shop.mapper.Mapper;
import gr.aueb.cf.e_shop.repository.CustomerRepository;
import gr.aueb.cf.e_shop.repository.UserRepository;
import gr.aueb.cf.e_shop.service.exceptions.CustomerAlreadyExistsException;
import gr.aueb.cf.e_shop.service.exceptions.CustomerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public Customer registerCustomer(RegisterCustomerDTO dto) throws CustomerAlreadyExistsException {
        Customer customer;
        User user;

        try {
            customer = Mapper.extractCustomerFromRegisterCustomerDTO(dto);

            // add user into customers entity and encrypt password
            user = Mapper.extractUserFromRegisterCustomerDTO(dto);
            user.setPassword(passwordEncoder.encode(dto.getPassword()));

            Optional<User> returnedUser = userRepository.findByUsername(dto.getUsername());
            if (returnedUser.isPresent()) throw new CustomerAlreadyExistsException(dto.getUsername());

            customer.addUser(user);
            customerRepository.save(customer);
            log.info("Customer with username " + customer.getUser().getUsername() + " inserted");
        } catch (CustomerAlreadyExistsException e) {
            log.error(e.getMessage());
            throw e;
        }
        return customer;
    }



    @Override
    public List<Customer> findAllCustomers() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findByLastname(String lastname) throws CustomerNotFoundException {

        try {
            List<Customer> customers = customerRepository.findByLastnameContainingIgnoreCase(lastname);
            if (customers.isEmpty()) {
                throw new CustomerNotFoundException(lastname);
            }
            log.info("Customer with lastname: " + lastname + " was found.");
            return customers;
        } catch (CustomerNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }

    }
}
