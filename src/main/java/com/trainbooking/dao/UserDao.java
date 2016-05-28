package com.trainbooking.dao;


import com.trainbooking.entity.User;


public interface UserDao extends Dao<User>{
    User findByLogin(String login);
}
