package org.example.order.service;

import org.example.common.model.CreateOrderRequest;
import org.example.common.model.DatabaseOrderInfo;
import org.example.order.dao.JdbcOrderDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JdbcOrderService {
    private final JdbcOrderDao jdbcOrderDao;

    public JdbcOrderService(JdbcOrderDao jdbcOrderDao) {
        this.jdbcOrderDao = jdbcOrderDao;
    }

    public List<DatabaseOrderInfo> findAllOrders() {
        return jdbcOrderDao.findAll();
    }

    public int createOrder(CreateOrderRequest order) {
        return jdbcOrderDao.insert(order);
    }

    @Transactional
    public int createOrders(List<CreateOrderRequest> orders) {
        jdbcOrderDao.insertBatch(orders);
        return orders.size();
    }
}
