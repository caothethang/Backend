package com.garden.root.services;

import com.garden.root.dto.TreeDTO;
import com.garden.root.entity.Category;
import com.garden.root.entity.Tree;
import com.garden.root.entity.User;
import com.garden.root.mapper.TreeConverter;
import com.garden.root.repositories.TreeRepository;
import com.garden.root.request.TreeRequest;
import com.garden.root.response.BaseResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreeImpl implements TreeServices {
    
    private final TreeRepository treeRepository;
    private final CategoryServices categoryServices;
    private final TreeConverter treeConverter;
    private final UserService userService;
    
    
    public TreeImpl(TreeRepository treeRepository, CategoryServices categoryServices, TreeConverter treeConverter, UserService userService) {
        this.treeRepository = treeRepository;
        this.categoryServices = categoryServices;
        this.treeConverter = treeConverter;
        this.userService = userService;
    }
    
    @Override
    public BaseResponse getListTree() {
        List<Tree> treeList = treeRepository.findAll();
        List<TreeDTO> treeDTOList = new ArrayList<>();
        treeList.forEach(tree -> {
            treeDTOList.add(treeConverter.convertToDto(tree));
        });
        BaseResponse<List<TreeDTO>> response = new BaseResponse<>();
        response.setData(treeDTOList);
        return response;
    }
    
    @Override
    public BaseResponse<TreeDTO> getTreeById(Long id) {
        Tree tree = treeRepository.findById(id).get();
        TreeDTO dto = treeConverter.convertToDto(tree);
        BaseResponse<TreeDTO> response = new BaseResponse();
        response.setData(dto);
        return response;
    }
    
    @Override
    public BaseResponse insertNewTree(TreeRequest treeRequest) {
        Category category = categoryServices.getCategoryById(treeRequest.getCategoryId()).getData();
        User user = userService.getUserById(treeRequest.getUserId()).getData();
        Tree tree = Tree.builder()
                .name(treeRequest.getName())
                .description(treeRequest.getDescription())
                .price(treeRequest.getPrice())
                .quantity(treeRequest.getQuantity())
                .createdAt(System.currentTimeMillis())
                .imageUri(treeRequest.getImageUri())
                .category(category)
                .user(user)
                .build();
        treeRepository.save(tree);
        BaseResponse resp = new BaseResponse();
        resp.setSuccess();
        return resp;
    }
    
    @Override
    public BaseResponse updateTree(TreeRequest treeRequest, Long id) {
        Category category = categoryServices.getCategoryById(treeRequest.getCategoryId()).getData();
        Tree tree = treeRepository.findById(id).get();
        tree = Tree.builder()
                .id(tree.getId())
                .name(treeRequest.getName())
                .description(treeRequest.getDescription())
                .price(treeRequest.getPrice())
                .quantity(treeRequest.getQuantity())
                .createdAt(tree.getCreatedAt())
                .updatedAt(System.currentTimeMillis())
                .imageUri(treeRequest.getImageUri())
                .category(category)
                .build();
        treeRepository.save(tree);
        BaseResponse response = new BaseResponse();
        response.setRd("Update success");
        response.setRc(200);
        return response;
    }
    
    @Override
    public BaseResponse deleteTree(Long id) {
        treeRepository.deleteById(id);
        BaseResponse response = new BaseResponse();
        response.setSuccess();
        return response;
    }
    
    
}
