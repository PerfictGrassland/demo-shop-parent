package org.example.order.dao;

import org.example.common.model.CreateOrderRequest;
import org.example.common.model.DatabaseOrderInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcOrderDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<DatabaseOrderInfo> findAll() {
        return jdbcTemplate.query(
                "SELECT o.id, o.user_id, u.name AS user_name, o.product, o.amount, o.order_date " +
                        "FROM orders o LEFT JOIN `user` u ON o.user_id = u.id ORDER BY o.id",
                (resultSet, rowNum) -> {
                    Timestamp orderTimestamp = resultSet.getTimestamp("order_date");
                    return new DatabaseOrderInfo(
                            resultSet.getLong("id"),
                            resultSet.getLong("user_id"),
                            resultSet.getString("user_name"),
                            resultSet.getString("product"),
                            resultSet.getBigDecimal("amount"),
                            orderTimestamp == null ? null : orderTimestamp.toLocalDateTime());
                });
    }

    public int insert(CreateOrderRequest order) {
        return jdbcTemplate.update(
                "INSERT INTO orders (user_id, product, amount, order_date) " +
                            "VALUES (?, ?, ?, CURRENT_TIMESTAMP)",
                order.getUserId(), order.getProduct(), order.getAmount());
    }

    public void insertBatch(List<CreateOrderRequest> orders) {
        List<Object[]> batchArgs = new ArrayList<Object[]>(orders.size());
        for (CreateOrderRequest order : orders) {
            batchArgs.add(new Object[]{
                    order.getUserId(), order.getProduct(), order.getAmount()
            });
        }
        jdbcTemplate.batchUpdate(
                "INSERT INTO orders (user_id, product, amount, order_date) " +
                            "VALUES (?, ?, ?, CURRENT_TIMESTAMP)",
                batchArgs);
    }
}
