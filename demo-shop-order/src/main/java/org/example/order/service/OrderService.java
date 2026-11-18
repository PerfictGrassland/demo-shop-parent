package org.example.order.service;

import org.example.common.model.OrderInfo;
import org.example.common.model.UserInfo;
import org.example.order.dao.OrderDao;
import org.example.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final UserService userService;
    private final OrderDao orderDao;

    public OrderService(UserService userService, OrderDao orderDao) {
        this.userService = userService;
        this.orderDao = orderDao;
    }

    public List<OrderInfo> findAllOrders() {
        return orderDao.findAll();
    }

    public OrderInfo createOrder(Long orderId, Long userId) {
        UserInfo user = userService.findUserById(userId);
        return new OrderInfo(orderId, user.getId(), user.getName(),
                "订单由 demo-shop-order 模块创建");
    }
}
