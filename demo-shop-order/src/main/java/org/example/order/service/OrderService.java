package org.example.order.service;

import org.example.common.model.OrderInfo;
import org.example.common.model.UserInfo;
import org.example.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final UserService userService;

    public OrderService(UserService userService) {
        this.userService = userService;
    }

    public OrderInfo createOrder(Long orderId, Long userId) {
        UserInfo user = userService.findUserById(userId);
        return new OrderInfo(orderId, user.getId(), user.getName(),
                "订单由 demo-shop-order 模块创建");
    }
}
