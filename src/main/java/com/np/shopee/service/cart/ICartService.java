package com.np.shopee.service.cart;

import java.math.BigDecimal;

import com.np.shopee.model.Cart;

public interface ICartService {
    Cart getCartById(Long id);

    void clearCart(Long id);

    BigDecimal getTotalPrice(Long id);

    Long initializeNewCart();

    Cart getCartByUserId(Long userId);
}
