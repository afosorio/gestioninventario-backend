package com.co.flypass.gestioninventario.infrastructure.persistence.customer;

import com.co.flypass.gestioninventario.domain.customer.Customer;
import com.co.flypass.gestioninventario.domain.customer.CustomerRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositoryImpl extends CrudRepository<CustomerEntity, Long>, CustomerRepository {

    @Override
    default void save(Customer customer) {
        save(CustomerEntity.fromDomain(customer));
    }
}
