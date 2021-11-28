package com.garden.root.services;


import com.garden.root.request.UserRequest;
import com.garden.root.response.BaseResponse;

public interface UserService {
    
    BaseResponse registerUser(UserRequest userRequest);
    
    BaseResponse userLogin(UserRequest userRequest);
}
