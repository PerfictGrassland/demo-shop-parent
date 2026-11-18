package org.example.web;

import org.example.common.model.DatabaseOrderInfo;
import org.example.common.model.DatabaseUserInfo;
import org.example.common.model.CreateOrderRequest;
import org.example.common.model.CreateUserRequest;
import org.example.common.model.OrderInfo;
import org.example.common.model.UserInfo;
import org.example.order.service.OrderService; // ? 子模块服务
import org.example.order.service.JdbcOrderService;
import org.example.user.service.UserService; // ? 子模块服务
import org.example.user.service.JdbcUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {
    private final UserService userService;  // ? 子模块服务注入
    private final OrderService orderService;  // ? 子模块服务注入
    private final JdbcUserService jdbcUserService;
    private final JdbcOrderService jdbcOrderService;

    public DemoController(UserService userService, OrderService orderService,
                          JdbcUserService jdbcUserService, JdbcOrderService jdbcOrderService) {
        this.userService = userService; // ? 子模块服务实例化
        this.orderService = orderService; // ? 子模块服务实例化
        this.jdbcUserService = jdbcUserService;
        this.jdbcOrderService = jdbcOrderService;
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

    @GetMapping("/db/users")  // ? 获取数据库用户列表: http://localhost:8081/demo/db/users
    public List<DatabaseUserInfo> getDatabaseUsers() {
        return jdbcUserService.findAllUsers();
    }

    @GetMapping("/db/users/{id}")  // ? 获取数据库用户信息: http://localhost:8081/demo/db/users/1

    public DatabaseUserInfo getDatabaseUser(@PathVariable("id") Long id) {
        return jdbcUserService.findUserById(id);
    }

    @PostMapping("/db/users") // ? 创建数据库用户: http://localhost:8081/demo/db/users
    public int createDatabaseUser(@RequestBody CreateUserRequest user) {
        return jdbcUserService.createUser(user);
    }
    //    @RequestBody 接收 JSON
    //    body: Raw
    //    {
    //        "name": "王五",
    //        "age": 25,
    //        "email": "wangwu33@example.com"
    //    }

    @PostMapping("/db/users/batch") // ? 创建数据库用户: http://localhost:8081/demo/db/users/batch
    public int createDatabaseUsers(@RequestBody List<CreateUserRequest> users) {
        return jdbcUserService.createUsers(users);
    }
    //    body: Raw
    //  [
    //    {
    //        "userId": 1,
    //            "product": "键盘",
    //            "amount": 299.00
    //    },
    //    {
    //        "userId": 1,
    //            "product": "鼠标",
    //            "amount": 89.50
    //    }
    //  ]

    @GetMapping("/db/orders")  // ? 获取数据库订单列表: http://localhost:8081/demo/db/orders
    public List<DatabaseOrderInfo> getDatabaseOrders() {
        return jdbcOrderService.findAllOrders();
    }

    @PostMapping("/db/orders")  // ? 创建数据库订单: http://localhost:8081/demo/db/orders
    public int createDatabaseOrder(@RequestBody CreateOrderRequest order) {
        return jdbcOrderService.createOrder(order);
    }

//    {
//        "userId": 3,
//        "product": "键盘",
//        "amount": 299.00
//    }

    @PostMapping("/db/orders/batch")  // ? 创建数据库订单: http://localhost:8081/demo/db/orders/batch
    public int createDatabaseOrders(@RequestBody List<CreateOrderRequest> orders) {
        return jdbcOrderService.createOrders(orders);
    }

//    [
//        {
//            "userId": 1,
//                "product": "键盘",
//                "amount": 299.00
//        },
//        {
//            "userId": 1,
//                "product": "鼠标",
//                "amount": 89.50
//        }
//    ]
}
