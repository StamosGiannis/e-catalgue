package gr.aueb.cf.e_shop.service.exceptions;

import java.io.Serial;

public class CustomerNotFoundException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(String lastname) {
        super("Customer with lastname: " + lastname + " was not found");
    }

}
