package com.co.flypass.gestioninventario.infrastructure.persistence.customer;

import com.co.flypass.gestioninventario.domain.customer.Customer;
import com.co.flypass.gestioninventario.domain.product.Product;
import com.co.flypass.gestioninventario.infrastructure.persistence.cateogry.CategoryEntity;
import com.co.flypass.gestioninventario.infrastructure.persistence.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_customers")
public class CustomerEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    public static CustomerEntity fromDomain(final Customer customer) {
        return new CustomerEntity(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getEmail()
        );
    }

    public Customer toDomain() {
        return new Customer(
                this.id,
                this.name,
                this.phone,
                this.address,
                this.email
        );
    }
}
