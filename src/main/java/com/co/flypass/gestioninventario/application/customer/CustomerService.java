package com.co.flypass.gestioninventario.application.customer;

import com.co.flypass.gestioninventario.domain.customer.Customer;
import com.co.flypass.gestioninventario.domain.customer.CustomerRepository;
import com.co.flypass.gestioninventario.exception.NoDataFoundException;
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

    public void existCustomer(long id){

        if(customerRepository.findCustomerById(id).isEmpty()){
            throw new NoDataFoundException("Customer no encontrado");
        }
    }
}
