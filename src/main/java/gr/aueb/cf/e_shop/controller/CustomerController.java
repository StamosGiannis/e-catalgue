package gr.aueb.cf.e_shop.controller;

import gr.aueb.cf.e_shop.Model.Product;
import gr.aueb.cf.e_shop.dto.ProductReadOnlyDTO;
import gr.aueb.cf.e_shop.mapper.Mapper;
import gr.aueb.cf.e_shop.service.CustomerServiceImpl;
import gr.aueb.cf.e_shop.service.ProductServiceImpl;
import gr.aueb.cf.e_shop.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.e_shop.service.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class CustomerController {

    private final ProductServiceImpl productService;
    private final CustomerServiceImpl customerService;

    @GetMapping("/customers/dashboard")
    public String dashboard(Model model) {
        List<Product> products;
        try {
            products = productService.findAllProducts();
            List<ProductReadOnlyDTO> productsDTO = new ArrayList<>();
            for (Product product : products) {
                productsDTO.add(Mapper.mapToProductReadOnlyDTO(product));
            }
            model.addAttribute("productsDTO", productsDTO);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "Product not found");
            return showError();
        }

        return "customers/dashboard";
    }

    @GetMapping("/customers/search")
    public String searchProducts(@RequestParam(value = "productName",required = false)String productName, Model model) throws ProductNotFoundException {

        try {
            List<Product> products = productService.getProductByProductName(productName);
            List<ProductReadOnlyDTO> productsDTO = new ArrayList<>();
            for (Product product : products) {
                productsDTO.add(Mapper.mapToProductReadOnlyDTO(product));
            }
            model.addAttribute("productsDTO", productsDTO);

        } catch (ProductNotFoundException e) {
            model.addAttribute("errorMessage", "No products found with the name: " + productName);
            return showError();
        }
        return "customers/search";
    }

    @GetMapping("/customers/error")
    public String showError() {
        return "customers/error";
    }
}
