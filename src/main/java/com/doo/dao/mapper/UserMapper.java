package com.doo.dao.mapper;

import com.doo.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sam Raju on 31/12/2021
 */
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getInt("id")
                ,rs.getString("name")
                ,rs.getString("email")
                ,rs.getString("username")
                ,rs.getString("password")
                );
    }
}
