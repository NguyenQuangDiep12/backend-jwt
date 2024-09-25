package com.np.shopee.service.cart;

import com.np.shopee.model.CartItem;

public interface ICartItemService {
    void addItemToCart(Long cardId, Long ProductId, int quantity);

    void removeItemFromCart(Long cartId, Long productId);

    void updateItemQuantity(Long cartId, Long productId, int quantity);

    CartItem getCartItem(Long cartId, Long productId);
}
