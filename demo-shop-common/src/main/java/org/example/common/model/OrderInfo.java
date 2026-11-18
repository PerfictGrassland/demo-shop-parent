package org.example.common.model;

public class OrderInfo {
    private Long id;
    private Long userId;
    private String userName;
    private String description;

    public OrderInfo() {
    }

    public OrderInfo(Long id, Long userId, String userName, String description) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
