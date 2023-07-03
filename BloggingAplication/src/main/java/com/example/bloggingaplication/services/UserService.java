package com.example.bloggingaplication.services;

import com.example.bloggingaplication.entity.User;
import com.example.bloggingaplication.payloads.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public UserDto createUser(UserDto user);
    public UserDto updateUser(UserDto user, Integer userId);
    public UserDto getUserById(Integer id);
    public List<UserDto> getAllUsers();
    public void deleteUser(Integer userId);

    public UserDto getUserByEmail(String email);

    public UserDto registerUser(UserDto userDto);
}
