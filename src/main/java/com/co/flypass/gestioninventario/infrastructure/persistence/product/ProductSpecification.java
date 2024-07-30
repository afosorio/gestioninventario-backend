package com.co.flypass.gestioninventario.infrastructure.persistence.product;

import com.co.flypass.gestioninventario.domain.cateogry.Category;
import com.co.flypass.gestioninventario.infrastructure.persistence.cateogry.CategoryEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductSpecification {

    public static Specification<ProductEntity> get(int category, LocalDate startDate, LocalDate endDate) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(category)) {
                CategoryEntity categoryEntity = new CategoryEntity();
                categoryEntity.setId(category);
                predicates.add(cb.equal(root.get("category"), categoryEntity));
            }

            if(Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdDate"), startDate));
                predicates.add(cb.lessThanOrEqualTo(root.get("createdDate"), endDate));
            }

            return cb.and(predicates.toArray(new Predicate[] {}));
        };
    }
}
