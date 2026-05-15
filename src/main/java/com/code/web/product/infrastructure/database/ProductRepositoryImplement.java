package com.code.web.product.infrastructure.database;

import com.code.web.product.domain.entity.Product;
import com.code.web.product.domain.port.ProductRepository;
import com.code.web.product.infrastructure.database.entity.ProductEntity;
import com.code.web.product.infrastructure.database.mapper.ProductEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryImplement implements ProductRepository {

    private final List<ProductEntity> productEntities = new ArrayList<>();

    private final ProductEntityMapper mapper;


    @Override
    public void upsert(Product product) {
        ProductEntity entity = mapper.mapToProductEntity(product);
        productEntities.add(entity);
    }

    @Override
    @Cacheable(value = "productEntities", key = "#id")
    public Optional<Product> findById(Long id) {
        log.info("Finding product with id: {}",id);
        return productEntities
                .stream()
                .filter(product -> Objects.equals(product.getId(), id))
                .findFirst()
                .map(mapper::mapToProduct);
    }

    @Override
    public List<Product> findAll() {

        List<Product> list = productEntities
                .stream()
                .map(mapper::mapToProduct)
                .toList();


        return list;

    }

    @Override
    @CacheEvict(value = "productEntities", key = "#id")
    public void deleteById(Long i

                           /. d) {
        productEntities.removeIf(product -> Objects.equals(product.getId(), id));
    }
}
