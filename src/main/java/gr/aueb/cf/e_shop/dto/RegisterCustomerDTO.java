package gr.aueb.cf.e_shop.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterCustomerDTO {

    @NotBlank(message = "Το πεδίο είναι υποχρεωτικό")
    @Size(min = 5, max = 32)
    private String username;

    @NotBlank(message = "Το πεδίο είναι υποχρεωτικό")
    @Pattern(regexp = "^.*(?=.{8,})(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).*$", message = "Ο κωδικός πρέπει να περιλαμβάνει Τουλάχιστον 8 χαρακτήρες.\n" +
            "Τουλάχιστον ένα ψηφίο.\n" +
            "Τουλάχιστον ένα πεζό γράμμα.\n" +
            "Τουλάχιστον ένα κεφαλαίο γράμμα.\n" +
            "Τουλάχιστον ένα ειδικό χαρακτήρα από το σύνολο !@#$%^&+=."
    )
    private String password;

    @NotBlank(message = "Το πεδίο είναι υποχρεωτικό")
    @Pattern(regexp = "^.*(?=.{8,})(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).*$", message = "Ο κωδικός πρέπει να περιλαμβάνει Τουλάχιστον 8 χαρακτήρες.\n" +
            "Τουλάχιστον ένα ψηφίο.\n" +
            "Τουλάχιστον ένα πεζό γράμμα.\n" +
            "Τουλάχιστον ένα κεφαλαίο γράμμα.\n" +
            "Τουλάχιστον ένα ειδικό χαρακτήρα από το σύνολο !@#$%^&+=."
    )
    private String confirmPassword;

    @NotBlank(message = "Το πεδίο είναι υποχρεωτικό")
    private String firstname;

    @NotBlank(message = "Το πεδίο είναι υποχρεωτικό")
    private String lastname;

    @NotBlank(message = "Το πεδίο είναι υποχρεωτικό")
    @Email
    private String email;

    @NotBlank(message = "Το πεδίο είναι υποχρεωτικό")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Ο αριθμός τηλεφώνου πρέπει να έχει από 10 εως 15 ψηφία")
    private String phoneNumber;

}
