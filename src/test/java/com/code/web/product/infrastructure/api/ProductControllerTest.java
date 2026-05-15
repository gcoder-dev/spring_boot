package com.code.web.product.infrastructure.api;

import com.code.web.common.mediator.Mediator;
import com.code.web.product.application.query.getAll.GetAllProductRequest;
import com.code.web.product.application.query.getAll.GetAllProductResponse;
import com.code.web.product.domain.entity.Product;
import com.code.web.product.infrastructure.api.dtos.ProductDTO;
import com.code.web.product.infrastructure.api.mappers.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private Mediator mediator;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductController productController;

    @Test
    public void getAllProducts(){

        GetAllProductResponse productResponse = new GetAllProductResponse(
            List.of(
                    Product.builder().id(1L).name("mr.white").description("He doesn't have descriptions").price(777.33).image("/image/image2.png").build(),
                    Product.builder().id(1L).name("mr.white").description("He doesn't have descriptions").price(777.33).image("/image/image2.png").build(),
                    Product.builder().id(1L).name("mr.white").description("He doesn't have descriptions").price(777.33).image("/image/image2.png").build(),
                    Product.builder().id(1L).name("mr.white").description("He doesn't have descriptions").price(777.33).image("/image/image2.png").build(),
                    Product.builder().id(1L).name("mr.white").description("He doesn't have descriptions").price(777.33).image("/image/image2.png").build()
            )
        );

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);

        Mockito.when(mediator.dispatch(any(GetAllProductRequest.class))).thenReturn(productResponse);
        Mockito.when(productMapper.mapToProductDTO(any(Product.class))).thenReturn(productDTO);

        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts("5");

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());

        List<ProductDTO> products = response.getBody();
        assertEquals(5, products.size());
   }
}