package com.garden.root.services;

import com.garden.root.dto.TreeDTO;
import com.garden.root.entity.Tree;
import com.garden.root.repositories.TreeRepository;
import com.garden.root.request.TreeRequest;
import org.springframework.stereotype.Service;

@Service
public class TreeImpl implements TreeServices {
    
    private final TreeRepository treeRepository;
    
    public TreeImpl(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }
    
    @Override
    public TreeDTO insertNewTree(TreeRequest treeRequest) {
        Tree tree = Tree.builder()
                .name(treeRequest.getName())
                .description(treeRequest.getDescription())
                .price(treeRequest.getPrice())
                .quantity(treeRequest.getQuantity())
                
                .build();
        
        return null;
    }
}
