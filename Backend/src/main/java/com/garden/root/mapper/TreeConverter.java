package com.garden.root.mapper;

import com.garden.root.dto.TreeDTO;
import com.garden.root.entity.Tree;

public interface TreeConverter {
    
    TreeDTO convertToDto(Tree tree);
}
