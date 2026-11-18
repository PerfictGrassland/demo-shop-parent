package org.example.common.model;

import java.math.BigDecimal;

public class CreateOrderRequest {
    private Long userId;
    private String product;
    private BigDecimal amount;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(Long userId, String product, BigDecimal amount) {
        this.userId = userId;
        this.product = product;
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
