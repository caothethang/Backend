package com.ecommerce.root.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {
    
    @GetMapping("/")
    public List<String> getClients() {
        return Arrays.asList("First Client", "Second Client");
    }
}
