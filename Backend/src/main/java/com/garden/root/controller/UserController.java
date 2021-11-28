package com.garden.root.controller;

import com.garden.root.request.UserRequest;
import com.garden.root.response.BaseResponse;
import com.garden.root.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRequest userRequest){
        BaseResponse response = userService.registerUser(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequest userRequest){
        BaseResponse response = userService.userLogin(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
