package gr.aueb.cf.e_shop.Model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends AbstractEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private String password;

    public static User NEW_CUSTOMER(String username, String password) {
        User user = new User();
        user.setIsActive(true);
        user.setRole(Role.ROLE_CUSTOMER);
        user.setStatus(Status.APPROVED);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    public static User NEW_ADMIN(String username, String password) {
        User user = new User();
        user.setIsActive(true);
        user.setRole(Role.ROLE_ADMIN);
        user.setStatus(Status.APPROVED);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    @OneToOne(mappedBy = "user")
    private Customer customer;

    /*@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Getter(AccessLevel.PROTECTED)
    private List<Order> orders;

    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    public void deleteOrder(Order order) {
        orders.remove(order);
        order.setUser(null);
    }*/

    // convenient methods
    public void addCustomer(Customer customer) {
        this.customer = customer;
        customer.setUser(this);
    }

    public void deleteCustomer() {
        if (this.customer != null) {
            this.customer.setUser(null);
            this.customer = null;
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
