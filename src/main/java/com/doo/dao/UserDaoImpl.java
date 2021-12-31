package com.doo.dao;

import com.doo.dao.mapper.UserMapper;
import com.doo.models.User;
import com.doo.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam Raju on 31/12/2021
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        try {
            jdbcTemplate.update(QueryUtils.ADD_USER, user.getName()
                    , user.getEmail()
                    , user.getUsername()
                    , user.getPassword()
            );
        }catch (DataAccessException exception){
            exception.printStackTrace();
        }

    }

    @Override
    public List<User> findAll() {
        try {
            return jdbcTemplate.query(QueryUtils.FIND_ALL_USERS, new UserMapper());
        }catch (DataAccessException exception){
            exception.printStackTrace();
            return new ArrayList<>();
        }

    }

    @Override
    public List<User> findById(int id) {
        try {
            return jdbcTemplate.query(QueryUtils.FIND_USER_BY_ID, new Object[] {id}, new UserMapper());
        }catch (DataAccessException exception){
            exception.printStackTrace();
            return new ArrayList<>();
        }

    }

    @Override
    public void delete(int id) {
        try {
            jdbcTemplate.update(QueryUtils.DELETE_USER, id);
        }catch (DataAccessException exception){
            exception.printStackTrace();
        }

    }
}
