package com.garden.root.mapper;

import com.garden.root.dto.TreeDTO;
import com.garden.root.entity.Tree;
import org.springframework.stereotype.Component;

@Component
public class TreeConverterImpl implements TreeConverter{
    @Override
    public TreeDTO convertToDto(Tree tree) {
        TreeDTO treeDTO = TreeDTO.builder()
                .id(tree.getId())
                .name(tree.getName())
                .description(tree.getDescription())
                .quantity(tree.getQuantity())
                .price(tree.getPrice())
                .createdAt(tree.getCreatedAt())
                .imageUri(tree.getImageUri())
                .categoryId(tree.getCategory().getId())
                .ownerId(tree.getUser().getId())
                .build();
        return treeDTO;
    }
}
