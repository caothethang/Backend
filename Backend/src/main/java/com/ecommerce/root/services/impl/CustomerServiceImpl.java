package com.ecommerce.root.services.impl;

import com.ecommerce.root.entity.CustomerProfile;
import com.ecommerce.root.repositories.CustomerProfileRepo;
import com.ecommerce.root.request.UpdateProfileRequest;
import com.ecommerce.root.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    
    private final CustomerProfileRepo customerProfileRepo;
    
    @Override
    public Object updateProfile(UpdateProfileRequest updateProfileRequest) {
        Long userId = updateProfileRequest.getUserId();
        CustomerProfile customerProfile = customerProfileRepo.findByUserId(userId);
        if (customerProfile == null){
            CustomerProfile e = CustomerProfile.builder()
                    .userId(userId)
                    .address(updateProfileRequest.getAddress())
                    .fullName(updateProfileRequest.getFullName())
                    .phoneNumber(updateProfileRequest.getPhoneNumber())
                    .createdAt(new Timestamp(System.currentTimeMillis()))
                    .build();
            customerProfileRepo.save(e);
        }else {
            customerProfile.setAddress(updateProfileRequest.getAddress());
            customerProfile.setFullName(updateProfileRequest.getFullName());
            customerProfile.setPhoneNumber(updateProfileRequest.getPhoneNumber());
            customerProfile.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            customerProfileRepo.save(customerProfile);
        }
        return customerProfile;
        
    }
    
    @Override
    public Object getProfile(Long userId) {
        CustomerProfile customerProfile = customerProfileRepo.findByUserId(userId);
        return customerProfile;
    }
}
