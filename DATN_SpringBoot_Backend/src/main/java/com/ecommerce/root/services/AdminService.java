package com.ecommerce.root.services;

import com.ecommerce.root.entity.User;
import com.ecommerce.root.response.UserResponse;

import java.util.List;

public interface AdminService{
    
    List<UserResponse> getListUser();
}
