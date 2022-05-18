package com.ecommerce.root.services.impl;

import com.ecommerce.root.common.HttpCodeDef;
import com.ecommerce.root.entity.BaseEntity;
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
import java.util.stream.Collectors;

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
        BaseResponse response = BaseResponse.builder().build();
        Optional<Category> optionalCategory = categoryRepository.findById(request.getCategoryId());
        if (! optionalCategory.isPresent()) {
            response.setFailed(HttpCodeDef.BAD_REQUEST, "Category Not Found");
        }
        String size = String.join(",", request.getSize());
        String color = String.join(",", request.getColor());
        Product product = Product.builder()
                .image(request.getImg())
                .name(request.getName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .hasFreeShipping(request.getHasFreeShipping())
                .description(request.getDescription())
                .size(size)
                .color(color)
                .category(optionalCategory.get())
                .source(request.getSource())
                .salePercentage(request.getSalePercentage())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        productRepository.save(product);
        response.setSuccess();
        return response;
    }
    
    @Override
    public List<ProductResponse> getAllProduct(String category, Long minPrice, Long maxPrice, String name, Integer sort, Integer pageIndex,Integer pageSize) {
        List<ProductResponse> productResponses = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Product> cq = cb.createQuery(Product.class);
            Root<Product> root = cq.from(Product.class);
            List<Predicate> listPredicate = new ArrayList<>();
            if (Objects.nonNull(category)) {
                listPredicate.add(cb.equal(root.get("category").get("name"), category));
            }
            if (Objects.nonNull(minPrice) && Objects.nonNull(maxPrice)) {
                listPredicate.add(cb.between(root.get("price"), minPrice, maxPrice));
            }
            if (Objects.nonNull(minPrice) && Objects.isNull(maxPrice)) {
                listPredicate.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (Objects.nonNull(name)) {
                listPredicate.add(cb.like(cb.upper(root.get("name")), "%" + name.toUpperCase() + "%"));
            }
            
            Path<Object> sortClause;
            Order order;
            
            if (Objects.nonNull(sort)) {
                
                switch (sort) {
                    case 0:
                        sortClause = root.get("name");
                        order = cb.asc(sortClause);
                        break;
                    case 1:
                        sortClause = root.get("name");
                        order = cb.desc(sortClause);
                        break;
                    case 2:
                        sortClause = root.get("price");
                        order = cb.asc(sortClause);
                        break;
                    case 3:
                        sortClause = root.get("price");
                        order = cb.desc(sortClause);
                        break;
                    default:
                        sortClause = root.get("name");
                        order = cb.desc(sortClause);
                }
            } else {
                sortClause = root.get("name");
                order = cb.desc(sortClause);
            }
            if (Objects.isNull(pageIndex)) {
                pageIndex = 0;
            }
            
            Predicate[] finalPredicate = new Predicate[listPredicate.size()];
            listPredicate.toArray(finalPredicate);
            TypedQuery<Product> query = em.createQuery(cq.select(root).where(cb.and(finalPredicate)).orderBy(order));
            if (Objects.nonNull(pageIndex) && Objects.nonNull(pageSize)){
                query.setMaxResults(pageSize);
                query.setFirstResult(pageIndex * pageSize);
            }
            
            List<Product> productList = query.getResultList();
            productList =  productList.stream().sorted(Comparator.comparing(BaseEntity::getCreatedAt).reversed()).collect(Collectors.toList());
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
    public ProductResponse updateProduct(UpdateProductRequest request) {
        String size = String.join(",", request.getSize());
        String color = String.join(",", request.getColor());
        Optional<Product> optionalProduct = productRepository.findById(request.getId());
        Optional<Category> optionalCategory = categoryRepository.findById(request.getCategoryId());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (Objects.nonNull(request.getName())){
                product.setName(request.getName());
            }
            if (Objects.nonNull(request.getDescription())){
                product.setDescription(request.getDescription());
            }
            if (Objects.nonNull(request.getImg())){
                product.setImage(request.getImg());
            }
            product.setPrice(request.getPrice());
            product.setQuantity(request.getQuantity());
            product.setCategory(optionalCategory.get());
            product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            product.setSalePercentage(request.getSalePercentage());
            product.setSize(size);
            product.setColor(color);
            product.setSource(request.getSource());
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
        if (! optionalProduct.isPresent()) {
            return null;
        }
        Product product = optionalProduct.get();
        ProductResponse productResponse = productMapper.toProductResponse(product);
        return productResponse;
    }
}
