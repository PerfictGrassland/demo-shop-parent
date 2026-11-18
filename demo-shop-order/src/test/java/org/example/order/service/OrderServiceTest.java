package org.example.order.service;

import org.example.common.model.OrderInfo;
import org.example.user.dao.UserDao;
import org.example.user.service.UserService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderServiceTest {
    @Test
    public void createsOrderByCallingUserModule() {
        UserService userService = new UserService(new UserDao());
        OrderService orderService = new OrderService(userService);

        OrderInfo order = orderService.createOrder(100L, 1L);

        assertEquals(Long.valueOf(100L), order.getId());
        assertEquals("张三", order.getUserName());
    }
}
