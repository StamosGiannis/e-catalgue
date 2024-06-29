package gr.aueb.cf.e_shop.repository;

import gr.aueb.cf.e_shop.Model.Role;
import gr.aueb.cf.e_shop.Model.Status;
import gr.aueb.cf.e_shop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
