package gr.aueb.cf.e_shop.dto;


import gr.aueb.cf.e_shop.Model.Role;
import gr.aueb.cf.e_shop.Model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReadOnlyDTO extends BaseDTO{

    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String phoneNumber;
    private String email;
    private Role role;
    private Status status;

}
