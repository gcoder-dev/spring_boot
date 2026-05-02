package com.code.web.product.infrastructure.database;

import com.code.web.product.domain.entity.Product;
import com.code.web.product.domain.port.ProductRepository;
import com.code.web.product.infrastructure.database.entity.ProductEntity;
import com.code.web.product.infrastructure.database.mapper.ProductEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImplement implements ProductRepository {

    private final List<ProductEntity> productEntities = new ArrayList<>();

    private final ProductEntityMapper mapper;


    @Override
    public void upsert(Product product) {
        ProductEntity entity = mapper.mapToProductEntity(product);
        productEntities.add(entity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productEntities
                .stream()
                .filter(product -> Objects.equals(product.getId(), id))
                .findFirst()
                .map(mapper::mapToProduct);
    }

    @Override
    public List<Product> findAll() {
        return productEntities
                .stream()
                .map(mapper::mapToProduct)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        productEntities.removeIf(product -> Objects.equals(product.getId(), id));
    }
}
