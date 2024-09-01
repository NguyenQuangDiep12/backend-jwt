package com.np.shopee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.np.shopee.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    
}
