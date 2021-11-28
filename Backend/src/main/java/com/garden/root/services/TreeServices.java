package com.garden.root.services;


import com.garden.root.dto.TreeDTO;
import com.garden.root.request.TreeRequest;

public interface TreeServices {
    
    TreeDTO insertNewTree(TreeRequest treeRequest);
}
