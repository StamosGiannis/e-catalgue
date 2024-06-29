package gr.aueb.cf.e_shop.service.exceptions;

import java.io.Serial;

public class CustomerAlreadyExistsException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public CustomerAlreadyExistsException(String username) {
        super("Customer with username: " + username + " already exists");
    }

}
