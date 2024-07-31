package com.co.flypass.gestioninventario.domain.cateogry;

import com.co.flypass.gestioninventario.domain.reservation.Reservation;

import java.util.Optional;

public interface CategoryRepository {

    void save(Category category);

    Optional<Category> findCategoryById(long id);
}
