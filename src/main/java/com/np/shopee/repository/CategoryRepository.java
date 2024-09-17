package com.np.shopee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.np.shopee.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByname(String name);

    // fix define query method
    @Query("SELECT u FROM Category u WHERE u.name = :CategoryName")
    boolean existByName(@Param("CategoryName") String name);

}
