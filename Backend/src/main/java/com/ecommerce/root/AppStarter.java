package com.ecommerce.root;

import com.ecommerce.root.entity.Role;
import com.ecommerce.root.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppStarter implements CommandLineRunner {
    
    private final RoleRepository roleRepository;
    
    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0){
            roleRepository.save(Role.builder()
                    .name("ROLE_ADMIN")
                    .build());
            roleRepository.save(Role.builder()
                    .name("ROLE_USER")
                    .build());
        }
    }
}
