package org.example.user.dao;

import org.example.common.model.CreateUserRequest;
import org.example.common.model.DatabaseUserInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcUserDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DatabaseUserInfo findById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, name, age, email FROM `user` WHERE id = ?",
                (resultSet, rowNum) -> new DatabaseUserInfo(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getObject("age") == null ? null : resultSet.getInt("age"),
                        resultSet.getString("email")),
                id);
    }

    public List<DatabaseUserInfo> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name, age, email FROM `user` ORDER BY id",
                (resultSet, rowNum) -> new DatabaseUserInfo(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getObject("age") == null ? null : resultSet.getInt("age"),
                        resultSet.getString("email")));
    }

    public int insert(CreateUserRequest user) {
        return jdbcTemplate.update(
                "INSERT INTO `user` (name, age, email) VALUES (?, ?, ?)",
                user.getName(), user.getAge(), user.getEmail());
    }

    public void insertBatch(List<CreateUserRequest> users) {
        List<Object[]> batchArgs = new ArrayList<Object[]>(users.size());
        for (CreateUserRequest user : users) {
            batchArgs.add(new Object[]{user.getName(), user.getAge(), user.getEmail()});
        }
        jdbcTemplate.batchUpdate(
                "INSERT INTO `user` (name, age, email) VALUES (?, ?, ?)", batchArgs);
    }
}
