package org.example.user.service;

import org.example.common.model.UserInfo;
import org.example.user.dao.UserDao;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {
    @Test
    public void findsUserThroughDao() {
        UserService userService = new UserService(new UserDao());

        UserInfo user = userService.findUserById(1L);

        assertEquals("张三", user.getName());
    }
}
