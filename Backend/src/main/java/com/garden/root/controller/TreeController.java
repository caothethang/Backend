package com.garden.root.controller;

import com.garden.root.dto.TreeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tree")
public class TreeController {
    
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
    public ResponseEntity createNewTree(TreeDTO treeDTO){
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
