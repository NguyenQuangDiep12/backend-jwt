package com.np.shopee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.np.shopee.model.Cart;

@Repository
public interface CartRepsitory extends JpaRepository<Cart, Long> {

}
