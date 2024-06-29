package gr.aueb.cf.e_shop.service;

import gr.aueb.cf.e_shop.Model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService {
    User getUserByUsername(String username) throws UsernameNotFoundException;
}
