package gr.aueb.cf.e_shop.controller;

import gr.aueb.cf.e_shop.Model.Customer;
import gr.aueb.cf.e_shop.Model.Product;
import gr.aueb.cf.e_shop.dto.CustomerReadOnlyDTO;
import gr.aueb.cf.e_shop.dto.ProductInsertDTO;
import gr.aueb.cf.e_shop.dto.ProductReadOnlyDTO;
import gr.aueb.cf.e_shop.dto.ProductUpdateDTO;
import gr.aueb.cf.e_shop.mapper.Mapper;
import gr.aueb.cf.e_shop.service.CustomerServiceImpl;
import gr.aueb.cf.e_shop.service.ProductServiceImpl;
import gr.aueb.cf.e_shop.service.UserServiceImpl;
import gr.aueb.cf.e_shop.service.exceptions.CustomerNotFoundException;
import gr.aueb.cf.e_shop.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.e_shop.service.exceptions.ProductNotFoundException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {


    private final ProductServiceImpl productService;
    private final CustomerServiceImpl customerService;

    @GetMapping("/admins/dashboard")
    public String dashboard(Model model) throws Exception{
        List<Customer> customers;
        try {
            customers = customerService.findAllCustomers();
            List<CustomerReadOnlyDTO> customersDTO = new ArrayList<>();
            for (Customer customer : customers) {
                customersDTO.add(Mapper.mapToCustomerReadOnlyDTO(customer));
            }
            model.addAttribute("customersDTO", customersDTO);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Customer not found");
            return showError();
        }

        return "/admins/dashboard";
    }

    @GetMapping("/admins/products/get")
    public String getProducts(Model model) throws EntityNotFoundException {
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

        return "admins/products/get";
    }


    @GetMapping("/admins/products/add")
    public String addProducts(Model model) {
        model.addAttribute("addProductForm", new ProductInsertDTO());
        return "admins/products/add";
    }

    @PostMapping("/admins/products/add")
    public String addProducts(@Valid @ModelAttribute("addProductForm") ProductInsertDTO insertDTO,
                              BindingResult bindingResult,Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            return  "admins/products/add";
        }

        try {
            Product createdProduct = productService.insertProduct(insertDTO);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Product not found");
            return showError();
        }

        return showSuccess();
    }

    @GetMapping("/admins/products/edit/{id}")
    public String editProducts(@PathVariable("id") Long id, Model model) throws EntityNotFoundException {

        ProductReadOnlyDTO readOnlyDTO = Mapper.mapToProductReadOnlyDTO(productService.getProductById(id));
        model.addAttribute("updateProductForm", readOnlyDTO);



        return "/admins/products/edit";
    }

    @PostMapping("/admins/products/edit/{id}")
    public String editProducts(@Valid @PathVariable("id") Long id, @ModelAttribute("updateProductForm")ProductUpdateDTO updateDTO
                               ,BindingResult bindingResult, Model model)
                                    throws EntityNotFoundException {
        if (bindingResult.hasErrors()) {
            // Επιστρέφουμε στη φόρμα επεξεργασίας αν υπάρχουν σφάλματα

            model.addAttribute("updateProductForm", updateDTO);
            model.addAttribute("id", id);
            return "/admins/products/edit/{id}";
        }

        try {

            Product updatedProduct = productService.updateProduct(updateDTO);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "Product not found");
            return showError();
        }

        return showSuccess();
    }

    @GetMapping("/admins/products/remove/{id}")
    public String confirmDeleteProduct(@PathVariable Long id, Model model) {
        try {
            Product product = productService.getProductById(id);
            ProductReadOnlyDTO productDTO = Mapper.mapToProductReadOnlyDTO(product);
            model.addAttribute("product", productDTO);

        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "Product not found");
            return showError();
        }
        return "/admins/products/remove";
    }

    @PostMapping("/admins/products/remove/{id}/confirm")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        try {
            productService.deleteProduct(id);

        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "Product not found");
            return showError();
        }
        return showSuccess();
    }

    @GetMapping("/admins/customers/search")
    public String searchCustomers(@RequestParam(value = "lastname", required = false)String lastname, Model model) throws CustomerNotFoundException {
        try {


            List<Customer>  customers = customerService.findByLastname(lastname);
            List<CustomerReadOnlyDTO> customersDTO = new ArrayList<>();
            for (Customer customer : customers) {
                customersDTO.add(Mapper.mapToCustomerReadOnlyDTO(customer));
            }
            model.addAttribute("customersDTO", customersDTO);
            return "admins/customers/search";
        } catch (CustomerNotFoundException e) {
            model.addAttribute("errorMessage", "No customers found with the name: " + lastname);
            return showError();
        }
    }

    @GetMapping("/admins/products/search")
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
        return "admins/products/search";
    }

    // returns success view
    @GetMapping("/admins/products/success")
    public String showSuccess() {
        return "admins/success";
    }

    // returns error view
    @GetMapping("/admins/products/error")
    public String showError() {
        return "admins/error";
    }
}
