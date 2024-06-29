package gr.aueb.cf.e_shop.controller;

import gr.aueb.cf.e_shop.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final IUserService userService;

    @GetMapping("/login")
    public String login(Model model, Principal principal, HttpServletRequest request) {
        if (principal == null) return "login";

        var user = userService.getUserByUsername(principal.getName());
        String role = user.getRole().name();

        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admins/dashboard";
        }

        if (role.equals("ROLE_CUSTOMER")) {
            return "redirect:/customers/dashboard";
        }

        return "redirect:/login";
    }

    @GetMapping("/")
    public String root(Principal principal) {
        return "redirect:/login";
    }
}
