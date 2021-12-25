package com.garden.root.services;


import com.garden.root.dto.TreeDTO;
import com.garden.root.request.TreeRequest;
import com.garden.root.response.BaseResponse;

public interface TreeServices {
    
    BaseResponse getListTree();
    
    BaseResponse<TreeDTO> getTreeById(Long id);
    
    BaseResponse insertNewTree(TreeRequest treeRequest);
    
    BaseResponse updateTree(TreeRequest treeRequest,Long id);
    
    BaseResponse deleteTree(Long id);
    
}
