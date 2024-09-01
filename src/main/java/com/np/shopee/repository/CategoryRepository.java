package com.np.shopee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.np.shopee.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByname(String name);

    boolean existByName(String name);

}
