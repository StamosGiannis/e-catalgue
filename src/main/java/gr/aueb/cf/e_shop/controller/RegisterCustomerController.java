package gr.aueb.cf.e_shop.controller;

import gr.aueb.cf.e_shop.Model.Customer;
import gr.aueb.cf.e_shop.dto.RegisterCustomerDTO;
import gr.aueb.cf.e_shop.service.ICustomerService;
import gr.aueb.cf.e_shop.service.IUserService;
import gr.aueb.cf.e_shop.service.exceptions.CustomerAlreadyExistsException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class RegisterCustomerController {

    private final IUserService userService;
    private final ICustomerService customerService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userForm", new RegisterCustomerDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("userForm") RegisterCustomerDTO dto,
                               BindingResult bindingResult)
            throws CustomerAlreadyExistsException {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            Customer createdCustomer = customerService.registerCustomer(dto);
        } catch (CustomerAlreadyExistsException e) {
            throw e;
        }
        return "redirect:/login";
    }

}
