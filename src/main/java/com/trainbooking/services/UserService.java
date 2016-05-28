package com.trainbooking.services;

import com.trainbooking.dto.UserDto;
import com.trainbooking.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService, EntityService<User>{
    List<UserDto> findAll();
    User findByLogin(String login);
}
