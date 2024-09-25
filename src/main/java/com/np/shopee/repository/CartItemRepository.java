package com.np.shopee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.np.shopee.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);
}
