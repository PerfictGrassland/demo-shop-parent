package org.example.user.service;

import org.example.common.model.UserInfo;
import org.example.user.dao.UserDao;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserServiceListTest {
    @Test
    public void returnsUserList() {
        UserService userService = new UserService(new UserDao());

        List<UserInfo> users = userService.findAllUsers();

        assertEquals(2, users.size());
        assertEquals("张三", users.get(0).getName());
    }
}
