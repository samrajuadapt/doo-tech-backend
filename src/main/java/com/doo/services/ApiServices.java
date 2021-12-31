package com.doo.services;

import com.doo.models.User;

import java.util.List;

/**
 * Created by Sam Raju on 31/12/2021
 */
public interface ApiServices {

    public int[] sortNumbers(int[] numbers);

    void saveUser(User user);
    User findUser(int id);
    List<User> findAllUsers();
    void deleteUser(int id);
}
