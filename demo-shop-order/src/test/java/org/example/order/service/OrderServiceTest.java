package org.example.order.service;

import org.example.common.model.OrderInfo;
import org.example.order.dao.OrderDao;
import org.example.user.dao.UserDao;
import org.example.user.service.UserService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderServiceTest {
    @Test
    public void createsOrderByCallingUserModule() {
        UserService userService = new UserService(new UserDao());
        OrderService orderService = new OrderService(userService, new OrderDao());

        OrderInfo order = orderService.createOrder(100L, 1L);

        assertEquals(Long.valueOf(100L), order.getId());
        assertEquals("张三", order.getUserName());
    }

    @Test
    public void returnsOrderList() {
        OrderService orderService = new OrderService(
                new UserService(new UserDao()), new OrderDao());

        List<OrderInfo> orders = orderService.findAllOrders();

        assertEquals(2, orders.size());
        assertEquals("键盘", orders.get(0).getDescription());
    }
}
