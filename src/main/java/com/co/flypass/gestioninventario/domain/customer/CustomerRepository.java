package com.co.flypass.gestioninventario.domain.customer;

import com.co.flypass.gestioninventario.domain.cateogry.Category;

import java.util.Optional;

public interface CustomerRepository {

    void save(Customer customer);

    Optional<Customer> findCustomerById(long id);
}
