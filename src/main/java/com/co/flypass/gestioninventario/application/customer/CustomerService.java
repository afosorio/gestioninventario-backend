package com.co.flypass.gestioninventario.application.customer;

import com.co.flypass.gestioninventario.domain.customer.Customer;
import com.co.flypass.gestioninventario.domain.customer.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void save(Customer customer){
        customerRepository.save(customer);
    }
}
