package com.ecommerce.root.services.impl;

import com.ecommerce.root.common.HttpCodeDef;
import com.ecommerce.root.entity.Category;
import com.ecommerce.root.entity.Product;
import com.ecommerce.root.entity.Sale;
import com.ecommerce.root.mapper.ProductMapper;
import com.ecommerce.root.repositories.CategoryRepository;
import com.ecommerce.root.repositories.ProductRepository;
import com.ecommerce.root.repositories.SaleRepository;
import com.ecommerce.root.request.CreateProductRequest;
import com.ecommerce.root.response.BaseResponse;
import com.ecommerce.root.response.ProductResponse;
import com.ecommerce.root.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    
    private final CategoryRepository categoryRepository;
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    
    @Override
    public BaseResponse createProduct(CreateProductRequest request) {
        BaseResponse response = new BaseResponse();
        Optional<Category> optionalCategory = categoryRepository.findById(request.getCategoryId());
        if (!optionalCategory.isPresent()){
            response.setFailed(HttpCodeDef.BAD_REQUEST,"Category Not Found");
        }
        Optional<Sale> optionalSale = saleRepository.findById(request.getSaleId());
        if (!optionalSale.isPresent()){
            response.setFailed(HttpCodeDef.BAD_REQUEST,"Sale Not Found");
        }
        
        Product product = Product.builder()
                .image(request.getImg())
                .name(request.getName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .description(request.getDescription())
                .category(optionalCategory.get())
                .sale(optionalSale.get())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        productRepository.save(product);
        response.setSuccess();
        return response;
    }
    
    @Override
    public List<ProductResponse> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> responseList = new ArrayList<>();
        if (Objects.nonNull(products)){
            products.forEach(product -> {
                responseList.add(productMapper.toProductResponse(product));
            });
        }
        return responseList;
    }
}
