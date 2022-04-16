package com.ecommerce.root.services.impl;

import com.ecommerce.root.common.RoleTypeEnum;
import com.ecommerce.root.entity.User;
import com.ecommerce.root.repositories.UserRepository;
import com.ecommerce.root.response.UserResponse;
import com.ecommerce.root.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    
    private final UserRepository userRepository;
    
    @Override
    public List<UserResponse> getListUser() {
        List<User> userList = userRepository.findAll();
        List<UserResponse> respone = new ArrayList<>();
        userList.forEach(user -> {
            user.getRoles().forEach(role -> {
                if (role.getName().equals(RoleTypeEnum.ROLE_USER)) {
                    respone.add(UserResponse.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .createdAt(user.getCreatedAt().getTime())
                            .build());
                }
            });
        });
        return respone;
    }
}
