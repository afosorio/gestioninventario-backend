package com.co.flypass.gestioninventario.controller;

import com.co.flypass.gestioninventario.application.customer.CustomerService;
import com.co.flypass.gestioninventario.domain.customer.Customer;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Response<Object> save(@Valid @RequestBody Customer customer) {
        customerService.save(customer);
        return new Response<>(HttpServletResponse.SC_OK, "Cliente creado exitosamente");
    }
}