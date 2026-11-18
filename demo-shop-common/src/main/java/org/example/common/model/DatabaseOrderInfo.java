package org.example.common.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DatabaseOrderInfo {
    private Long id;
    private Long userId;
    private String userName;
    private String product;
    private BigDecimal amount;
    private LocalDateTime orderDate;

    public DatabaseOrderInfo() {
    }

    public DatabaseOrderInfo(Long id, Long userId, String userName, String product,
                             BigDecimal amount, LocalDateTime orderDate) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.product = product;
        this.amount = amount;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
