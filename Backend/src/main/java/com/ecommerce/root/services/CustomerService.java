package com.ecommerce.root.services;

import com.ecommerce.root.request.UpdateProfileRequest;

public interface CustomerService {
    
    Object updateProfile(UpdateProfileRequest updateProfileRequest);
    
    Object getProfile(Long userId);
}
