package com.np.shopee.service.cart;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.np.shopee.exception.ResourceNotFoundException;
import com.np.shopee.model.Cart;
import com.np.shopee.model.CartItem;
import com.np.shopee.repository.CartItemRepository;
import com.np.shopee.repository.CartRepsitory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
    private final CartRepsitory cartRepsitory;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart getCartById(Long id) {
        Cart cart = cartRepsitory.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        BigDecimal totalAmout = cart.getTotalAmount();
        cart.setTotalAmount(totalAmout);
        return cartRepsitory.save(cart);

    }

    @Override
    public void clearCart(Long id) {
        Cart cart = getCartById(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cartRepsitory.deleteById(id);
    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCartById(id);
        return cart.getItems()
                // reduce(T identity , BinaryOperator<T> accumulator)
                .stream().map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

}
