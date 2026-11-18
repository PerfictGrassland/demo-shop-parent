package org.example.user.service;

import org.example.common.model.CreateUserRequest;
import org.example.common.model.DatabaseUserInfo;
import org.example.user.dao.JdbcUserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JdbcUserService {
    private final JdbcUserDao jdbcUserDao;

    public JdbcUserService(JdbcUserDao jdbcUserDao) {
        this.jdbcUserDao = jdbcUserDao;
    }

    public DatabaseUserInfo findUserById(Long id) {
        return jdbcUserDao.findById(id);
    }

    public List<DatabaseUserInfo> findAllUsers() {
        return jdbcUserDao.findAll();
    }

    public int createUser(CreateUserRequest user) {
        return jdbcUserDao.insert(user);
    }

    @Transactional
    public int createUsers(List<CreateUserRequest> users) {
        jdbcUserDao.insertBatch(users);
        return users.size();
    }
}
