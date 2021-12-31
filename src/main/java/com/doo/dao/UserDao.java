package com.doo.dao;

import com.doo.models.User;

import java.util.List;

/**
 * Created by Sam Raju on 31/12/2021
 */
public interface UserDao {
    void save(User user);
    List<User> findAll();
    List<User> findById(int id);
    void delete(int id);
}
