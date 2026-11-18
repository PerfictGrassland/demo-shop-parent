package org.example.web;

import org.example.common.model.OrderInfo;
import org.example.common.model.UserInfo;
import org.example.order.service.OrderService; // ? 子模块服务
import org.example.user.service.UserService; // ? 子模块服务
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {
    private final UserService userService;  // ? 子模块服务注入
    private final OrderService orderService;  // ? 子模块服务注入

    public DemoController(UserService userService, OrderService orderService) {
        this.userService = userService; // ? 子模块服务实例化
        this.orderService = orderService; // ? 子模块服务实例化
    }

    @GetMapping("/users") // ? 获取用户列表: http://localhost:8081/demo/users
    public List<UserInfo> getUsers() {
        return userService.findAllUsers(); // ? 调用用户子模块
    }

    @GetMapping("/users/{id}")  // ? 获取用户信息: http://localhost:8081/demo/users/1
    public UserInfo getUser(@PathVariable("id") Long id) { // ? 路径参数
        return userService.findUserById(id); // ? 调用子模块服务
    }

    @GetMapping("/orders") // ? 获取订单列表: http://localhost:8081/demo/orders
    public List<OrderInfo> getOrders() {
        return orderService.findAllOrders(); // ? 调用订单子模块
    }

    @GetMapping("/orders/{orderId}/users/{userId}")  // ? 创建订单: http://localhost:8081/demo/orders/1/users/2
    public OrderInfo createOrder(@PathVariable("orderId") Long orderId, // ? 路径参数
                                 @PathVariable("userId") Long userId) { // ? 路径参数
        return orderService.createOrder(orderId, userId); // ? 调用子模块服务
    }
}
