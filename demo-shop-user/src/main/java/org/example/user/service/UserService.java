package org.example.user.service;

import org.example.common.model.UserInfo;
import org.example.user.dao.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserInfo findUserById(Long id) {
        return userDao.findById(id);
    }

    public List<UserInfo> findAllUsers() {
        return userDao.findAll();
    }
}
