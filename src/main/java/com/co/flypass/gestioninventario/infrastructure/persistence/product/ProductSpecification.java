package com.co.flypass.gestioninventario.infrastructure.persistence.product;

import com.co.flypass.gestioninventario.domain.cateogry.Category;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductSpecification {

    public static Specification<ProductEntity> get(Category category, LocalDate startDate, LocalDate endDate) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(category)) {

                predicates.add(cb.equal(root.get("document.id"), category.getId()));
            }

            if (Objects.nonNull(startDate)) {
                predicates.add(cb.equal(root.get("createdDate"), startDate));
            }

            if (Objects.nonNull(endDate) ) {
                predicates.add(cb.equal(root.get("createdDate"), endDate));
            }
            return cb.and(predicates.toArray(new Predicate[] {}));
        };
    }
}
