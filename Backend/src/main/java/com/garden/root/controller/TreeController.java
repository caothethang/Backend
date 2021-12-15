package com.garden.root.controller;

import com.garden.root.dto.TreeDTO;
import com.garden.root.request.TreeRequest;
import com.garden.root.response.BaseResponse;
import com.garden.root.services.TreeServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/tree")
@CrossOrigin("*")
public class TreeController {
    
    private final TreeServices treeService;
    
    public TreeController(TreeServices treeService) {
        this.treeService = treeService;
    }
    
    @GetMapping
    public ResponseEntity getListTree(){
        BaseResponse response = treeService.getListTree();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping("/{tree-id}")
    public ResponseEntity getTreeDetailById(@PathVariable(value = "tree-id") Long id){
        BaseResponse response = treeService.getTreeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping("/")
    public ResponseEntity getTreeByType(@RequestParam(value = "type")String type){
        return null;
    }
    
    @PostMapping
    public ResponseEntity createNewTree(@RequestBody TreeRequest treeRequest){
        BaseResponse response = treeService.insertNewTree(treeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{tree-id}")
    public ResponseEntity updateTreeDetail(@RequestBody TreeRequest request,@PathVariable(value = "tree-id") Long treeId){
        BaseResponse response = treeService.updateTree(request,treeId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @DeleteMapping("/{tree-id}")
    public ResponseEntity deleteTree(@PathVariable(value = "tree-id") Long treeId){
        BaseResponse response = treeService.deleteTree(treeId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
