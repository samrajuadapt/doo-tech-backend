package com.doo.utils;

/**
 * Created by Sam Raju on 31/12/2021
 */
public class QueryUtils {

    public static String ADD_USER = "insert into users (name,email,username,password) values(?,?,?,?)";

    public static String FIND_ALL_USERS = "select * from users";

    public static String FIND_USER_BY_ID =  "select * from users where id = ?";

    public static String DELETE_USER = "delete from users where id = ?";
}
