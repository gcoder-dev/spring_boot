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
import com.code.web.product.infrastructure.api.dtos.CreateProductDTO;
import com.code.web.product.infrastructure.api.dtos.ProductDTO;
import com.code.web.product.infrastructure.api.dtos.UpdateProductDTO;
import com.code.web.product.infrastructure.api.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ProductAPI {

    private final Mediator mediator;
    private final ProductMapper productMapper;


    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestParam(required = false) String pageSize) {
        log.info("Getting all products");
        GetAllProductResponse response = mediator.dispatch(new GetAllProductRequest());
        List<ProductDTO> productDTOS = response.getProducts().stream().map(productMapper::mapToProductDTO).toList();
        log.info("Found {} products",productDTOS.size() );

        return ResponseEntity.ok().body(productDTOS);
    }


    @GetMapping("product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        log.info("Getting product with id: {} ",id);




        GetProductByIdResponse response = mediator.dispatch(new GetProductByIdRequest(id));

        Product product = response.getProduct();

        ProductDTO dto = productMapper.mapToProductDTO(product);


        log.info("Found product with id: {}",dto.getId());

        return ResponseEntity.ok(dto);

    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute CreateProductDTO productDTO) {

        log.info("Saving product with id: {}",productDTO.getId());
        CreateProductRequest request = productMapper.mapToCreateProductRequest(productDTO);

        mediator.dispatch(request);

        log.info("Saved product with id: {}", productDTO.getId());

        return ResponseEntity.created(URI.create("/api/v1/".concat(productDTO.getId().toString()))).build();
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@ModelAttribute UpdateProductDTO productDTO) {

        log.info("Updating product with id: {}",productDTO.getId());
        UpdateProductRequest request = productMapper.mapToUpdateProductRequest(productDTO);

        mediator.dispatch(request);
        log.info("Updated product with id {}",productDTO.getId());
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        log.info("Deleting product with id: {}",id);
        mediator.dispatchAsync(new DeleteProductRequest(id));
        log.info("Deleted product wiht id: {}",id);
        return ResponseEntity.accepted().build();
    }
}
