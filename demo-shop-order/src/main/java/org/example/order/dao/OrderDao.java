package org.example.order.dao;

import org.example.common.model.OrderInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDao {
    private final List<OrderInfo> orders = new ArrayList<OrderInfo>();

    public OrderDao() {
        orders.add(new OrderInfo(1001L, 1L, "张三", "键盘"));
        orders.add(new OrderInfo(1002L, 2L, "李四", "鼠标"));
    }

    public List<OrderInfo> findAll() {
        return new ArrayList<OrderInfo>(orders);
    }
}
