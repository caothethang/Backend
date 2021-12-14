package com.garden.root.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tree-type")
public class TreeTypeController {
    
    @GetMapping
    public RequestMapping getTreeType(){
        return null;
    }
    
}
