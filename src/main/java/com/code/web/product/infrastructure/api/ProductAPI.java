package com.code.web.product.infrastructure.api;

import com.code.web.product.infrastructure.api.dtos.CreateProductDTO;
import com.code.web.product.infrastructure.api.dtos.UpdateProductDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProductAPI {
    ResponseEntity<?> getAllProducts(@RequestParam(required = false) String pageSize);

    ResponseEntity<?> getProductById(@PathVariable Long id);

    ResponseEntity<?> create(@RequestBody CreateProductDTO productDTO);

    ResponseEntity<?> updateProduct(@RequestBody UpdateProductDTO productDTO);

    ResponseEntity<?> deleteProduct(@PathVariable Long id);
}
