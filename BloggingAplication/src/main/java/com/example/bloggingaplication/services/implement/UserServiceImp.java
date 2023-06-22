package com.example.bloggingaplication.services.implement;

import ch.qos.logback.core.pattern.ConverterUtil;
import com.example.bloggingaplication.entity.User;
import com.example.bloggingaplication.exceptions.ResourceNotFoundException;
import com.example.bloggingaplication.payloads.UserDto;
import com.example.bloggingaplication.repositories.UserRepository;
import com.example.bloggingaplication.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User userSaved = this.userRepository.save(user);
        return this.userToDto(userSaved);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User saved = this.userRepository.save(user);
        return this.userToDto(saved);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("User", " id ", id));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " id ", userId));
        this.userRepository.delete(user);
    }

    @Override
    public UserDto getUserByEmail(String email){
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User ", " email: " + email, 0));
        return this.userToDto(user);
    }

    private User dtoToUser(UserDto userDto){
        User user = new ModelMapper().map(userDto,User.class);
        return user;
    }
    public UserDto userToDto(User user){
        UserDto userDto = new ModelMapper().map(user, UserDto.class);
        return userDto;
    }
}
