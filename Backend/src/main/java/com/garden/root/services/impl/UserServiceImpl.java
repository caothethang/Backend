package com.garden.root.services.impl;

import com.garden.root.common.RoleUser;
import com.garden.root.common.UserStatus;
import com.garden.root.entity.User;
import com.garden.root.repositories.UserRepository;
import com.garden.root.request.UserRequest;
import com.garden.root.response.BaseResponse;
import com.garden.root.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public BaseResponse registerUser(UserRequest userRequest) {
        BaseResponse response =new BaseResponse();
        User existUser = userRepository.findUserByUserName(userRequest.getUsername());
        if (!Objects.isNull(existUser)){
            response.setFailed(UserStatus.REGISTER_FAILED,"Register failed",UserStatus.REGISTER_FAILED);
        }else {
            User user = User.builder()
                    .userName(userRequest.getUsername())
                    .password(userRequest.getPassword())
                    .createdAt(System.currentTimeMillis())
                    .role(RoleUser.CUSTOMER.getCode())
                    .build();
            userRepository.save(user);
            response.setSuccess();
            response.setStatus(UserStatus.REGISTER_SUCCESS);
        }
        return response;
    }
    
    @Override
    public BaseResponse userLogin(UserRequest userRequest) {
        BaseResponse baseResponse = new BaseResponse();
        String username = userRequest.getUsername();
        String password = userRequest.getPassword();
        User user = userRepository.findUserByUserNameAndPassword(username,password);
        if (Objects.isNull(user)){
            baseResponse.setFailed(UserStatus.LOGIN_FAILED,"Login failed",UserStatus.LOGIN_FAILED);
        }else {
            baseResponse.setRc(UserStatus.LOGIN_SUCCESS);
            baseResponse.setRd("Login success");
            return baseResponse;
        }
        return baseResponse;
    }
}
