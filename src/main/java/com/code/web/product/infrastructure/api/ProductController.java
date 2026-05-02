package com.code.web.product.infrastructure.api;

import com.code.web.common.mediator.Mediator;
import com.code.web.product.application.command.create.CreateProductRequest;
import com.code.web.product.application.command.delete.DeleteProductRequest;
import com.code.web.product.application.command.update.UpdateProductRequest;
import com.code.web.product.application.query.getAll.GetAllProductRequest;
import com.code.web.product.application.query.getAll.GetAllProductResponse;
import com.code.web.product.application.query.getById.GetProductByIdRequest;
import com.code.web.product.application.query.getById.GetProductByIdResponse;
import com.code.web.product.domain.entity.Product;
import com.code.web.product.infrastructure.api.dtos.ProductDTO;
import com.code.web.product.infrastructure.api.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController implements ProductAPI {

    private final Mediator mediator;
    private final ProductMapper productMapper;


    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts(@RequestParam(required = false) String pageSize) {
        GetAllProductResponse response = mediator.dispatch(new GetAllProductRequest());
        List<ProductDTO> productDTOS = response.getProducts().stream().map(productMapper::mapToProductDTO).toList();
        return ResponseEntity.ok(productDTOS);
    }


    @GetMapping("product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        GetProductByIdResponse response = mediator.dispatch(new GetProductByIdRequest(id));

        Product product = response.getProduct();

        ProductDTO dto = productMapper.mapToProductDTO(product);

        return ResponseEntity.ok(dto);

    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {
        CreateProductRequest request = productMapper.mapToCreateProductRequest(productDTO);

        mediator.dispatch(request);

        return ResponseEntity.created(URI.create("/api/v1/".concat(productDTO.getId().toString()))).build();
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) {
        UpdateProductRequest request = productMapper.mapToUpdateProductRequest(productDTO);

        mediator.dispatch(request);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        mediator.dispatch(new DeleteProductRequest(id));
        return null;
    }
}
