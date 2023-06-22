package com.example.bloggingaplication.controllers;

import com.example.bloggingaplication.entity.User;
import com.example.bloggingaplication.payloads.ApiResponse;
import com.example.bloggingaplication.payloads.UserDto;
import com.example.bloggingaplication.services.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //POST - CREATE
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto create = this.userService.createUser(userDto);
        return new ResponseEntity<>(create, HttpStatus.CREATED);
    }
    //PUT - update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid ){
        UserDto updated = this.userService.updateUser(userDto, uid);
        return ResponseEntity.ok(updated);
    }


    // DELETE - delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer id){
        userService.deleteUser(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted succesfully!", true),HttpStatus.OK);
    }
    // GET - list
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> listAllUser(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer uid){
        return ResponseEntity.ok(this.userService.getUserById(uid));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(this.userService.getUserByEmail(email));
    }
}
