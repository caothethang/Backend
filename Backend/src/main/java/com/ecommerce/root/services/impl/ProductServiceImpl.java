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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.util.*;

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
        String size = String.join(",",request.getSize());
        Product product = Product.builder()
                .image(request.getImg())
                .name(request.getName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .hasFreeShipping(request.getHasFreeShipping())
                .description(request.getDescription())
                .size(size)
                .category(optionalCategory.get())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        productRepository.save(product);
        response.setSuccess();
        return response;
    }
    
    @Override
    public List<ProductResponse> getAllProduct(String category, Long minPrice, Long maxPrice, String name,Integer sort ,Integer pageIndex) {
        List<ProductResponse> productResponses = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Product> cq = cb.createQuery(Product.class);
            Root<Product> root = cq.from(Product.class);
            List<Predicate> listPredicate = new ArrayList<>();
            if (Objects.nonNull(category)){
                listPredicate.add(cb.equal(root.get("category").get("name"),category));
            }
            if (Objects.nonNull(minPrice) && Objects.nonNull(maxPrice)){
                listPredicate.add(cb.between(root.get("price"),minPrice,maxPrice));
            }
            if (Objects.nonNull(name)){
                listPredicate.add(cb.like(cb.upper(root.get("name")),"%"+name.toUpperCase()+"%"));
            }
        
            Path<Object> sortClause = null;
            Order order = null;
            
            if (Objects.nonNull(sort)){
                
                switch (sort){
                    case 0:
                        sortClause = root.get("name");
                        order = cb.asc(sortClause);
                        break;
                    case 1:
                        sortClause = root.get("name");
                        order = cb.desc(sortClause);
                        break;
                    case 3:
                        sortClause = root.get("price");
                        order = cb.asc(sortClause);
                        break;
                    case 4:
                        sortClause = root.get("price");
                        order = cb.desc(sortClause);
                        break;
                    default:
                        sortClause = root.get("name");
                        order = cb.desc(sortClause);
                }
            }{
                sortClause = root.get("name");
                order = cb.desc(sortClause);
            }
            if (Objects.isNull(pageIndex)){
                pageIndex = 0;
            }
        
            Predicate[] finalPredicate = new Predicate[listPredicate.size()];
            listPredicate.toArray(finalPredicate);
            TypedQuery<Product> query = em.createQuery(cq.select(root).where(cb.and(finalPredicate)).orderBy(order));
            query.setMaxResults(10);
            query.setFirstResult(pageIndex * 10);
        
            List<Product> productList = query.getResultList();
            productResponses = new ArrayList<>();
            for (Product product : productList) {
                productResponses.add(productMapper.toProductResponse(product));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productResponses;
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
    
    @Override
    public ProductResponse getProductDetail(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            return null;
        }
        Product product = optionalProduct.get();
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .hasFreeShipping(product.isHasFreeShipping())
                .rate(product.getRate())
                .categoryName(product.getCategory().getName())
                .build();
        return productResponse;
    }
}
