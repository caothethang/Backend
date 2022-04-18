package com.ecommerce.root.services.impl;

import com.ecommerce.root.common.HttpCodeDef;
import com.ecommerce.root.entity.Category;
import com.ecommerce.root.entity.Product;
import com.ecommerce.root.mapper.ProductMapper;
import com.ecommerce.root.repositories.CategoryRepository;
import com.ecommerce.root.repositories.ProductRepository;
import com.ecommerce.root.request.CreateProductRequest;
import com.ecommerce.root.request.UpdateProductRequest;
import com.ecommerce.root.response.BaseResponse;
import com.ecommerce.root.response.ProductResponse;
import com.ecommerce.root.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public BaseResponse createProduct(CreateProductRequest request) {
        BaseResponse response = new BaseResponse();
        Optional<Category> optionalCategory = categoryRepository.findById(request.getCategoryId());
        if (!optionalCategory.isPresent()){
            response.setFailed(HttpCodeDef.BAD_REQUEST,"Category Not Found");
        }
        
        Product product = Product.builder()
                .image(request.getImg())
                .name(request.getName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .description(request.getDescription())
                .category(optionalCategory.get())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        productRepository.save(product);
        response.setSuccess();
        return response;
    }
    
    @Override
    public List<ProductResponse> getAllProduct(String category, Long minPrice, Long maxPrice, String name, Long pageIndex) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        List<Predicate> listPredicate = new ArrayList<>();
//        if ()
        return null;
    }
    
    @Override
    public ProductResponse updateProduct(Long productId, UpdateProductRequest request) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Optional<Category> optionalCategory = categoryRepository.findById(request.getCategoryId());
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product = Product.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .image(request.getImg())
                    .price(request.getPrice())
                    .quantity(request.getQuantity())
                    .category(optionalCategory.get())
                    .build();
            product = productRepository.save(product);
            return productMapper.toProductResponse(product);
        }
        return null;
    }
    
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
