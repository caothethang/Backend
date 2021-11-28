package com.garden.root.controller;

import com.garden.root.dto.TreeDTO;
import com.garden.root.request.TreeRequest;
import com.garden.root.services.TreeServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/tree")
public class TreeController {
    
    private final TreeServices treeService;
    
    public TreeController(TreeServices treeService) {
        this.treeService = treeService;
    }
    
    @GetMapping
    public ResponseEntity getListTree(){
        return null;
    }
    
    @GetMapping("/{tree-id}")
    public ResponseEntity getTreeDetailById(@PathVariable(value = "tree-id") Long id){
        return null;
    }
    
    @GetMapping("/")
    public ResponseEntity getTreeByType(@RequestParam(value = "type")String type){
        return null;
    }
    
    @PostMapping
    public ResponseEntity createNewTree(@RequestBody TreeRequest treeRequest){
        return null;
    }
    
    @PutMapping("/{tree-id}")
    public ResponseEntity updateTreeDetail(TreeDTO treeDTO,@PathVariable(value = "tree-id") Long treeId){
        return null;
    }
    
    @DeleteMapping("/{tree-id}")
    public ResponseEntity deleteTree(@PathVariable(value = "tree-id") Long treeId){
        return null;
    }
}
