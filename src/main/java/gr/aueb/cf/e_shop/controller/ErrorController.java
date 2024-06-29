package gr.aueb.cf.e_shop.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Locale;

@ControllerAdvice
public class ErrorController {



    @ExceptionHandler(value = { RuntimeException.class })
    public String handleBadRequest(Model model, RuntimeException ex) {
        model.addAttribute("err", ex);
        return "/error";
    }

    @ExceptionHandler( value = {NoResourceFoundException.class})
    public String handleException(NoResourceFoundException e, Model model) {
        model.addAttribute("err", "Η σελίδα δεν βρέθηκε");
        return "/error";
    }
}
