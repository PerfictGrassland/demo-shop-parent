package org.example.user.dao;

import org.example.common.model.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDao {
    private final Map<Long, UserInfo> users = new HashMap<Long, UserInfo>();

    public UserDao() {
        users.put(1L, new UserInfo(1L, "张三"));
        users.put(2L, new UserInfo(2L, "李四"));
    }

    public UserInfo findById(Long id) {
        UserInfo user = users.get(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + id);
        }
        return user;
    }
}
