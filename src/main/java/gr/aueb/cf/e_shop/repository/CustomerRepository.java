package gr.aueb.cf.e_shop.repository;

import gr.aueb.cf.e_shop.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastnameContainingIgnoreCase(String lastname);
}
