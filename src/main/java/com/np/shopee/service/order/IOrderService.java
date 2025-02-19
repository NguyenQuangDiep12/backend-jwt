package com.np.shopee.service.order;

import java.util.List;

import com.np.shopee.model.Order;

public interface IOrderService {
    Order placeOrder(Long userId);

    Order getOrder(Long orderId);

    List<Order> getUserOrders(Long userId);
}
