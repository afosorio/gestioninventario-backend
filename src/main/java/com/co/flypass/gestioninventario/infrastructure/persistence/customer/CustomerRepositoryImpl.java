package com.co.flypass.gestioninventario.infrastructure.persistence.customer;

import com.co.flypass.gestioninventario.domain.customer.Customer;
import com.co.flypass.gestioninventario.domain.customer.CustomerRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepositoryImpl extends CrudRepository<CustomerEntity, Long>, CustomerRepository {

    @Override
    default void save(Customer customer) {
        save(CustomerEntity.fromDomain(customer));
    }

    @Override
    default Optional<Customer> findCustomerById(long id) {
        return findById(id).map(CustomerEntity::toDomain);
    }
}
