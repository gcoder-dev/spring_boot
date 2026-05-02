package com.code.web.product.infrastructure.api;

import com.code.web.product.infrastructure.api.dtos.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProductAPI {
    ResponseEntity<?> getAllProducts(@RequestParam(required = false) String pageSize);

    ResponseEntity<?> getProductById(@PathVariable Long id);

    ResponseEntity<?> create(@RequestBody ProductDTO productDTO);

    ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO);

    ResponseEntity<?> deleteProduct(@PathVariable Long id);
}
