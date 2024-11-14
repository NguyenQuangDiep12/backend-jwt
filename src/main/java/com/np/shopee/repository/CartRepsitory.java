package com.np.shopee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.np.shopee.model.Cart;
import com.np.shopee.model.User;

@Repository
public interface CartRepsitory extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}
