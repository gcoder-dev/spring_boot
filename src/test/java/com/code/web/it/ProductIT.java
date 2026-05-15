package com.code.web.it;

import com.code.web.product.domain.entity.Product;
import com.code.web.product.domain.port.ProductRepository;
import com.code.web.product.infrastructure.api.dtos.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Slf4j
public class ProductIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp(){
        log.info("Setting log integration test");
        productRepository.upsert(
                Product
                        .builder()
                        .id(1L)
                        .name("product 1")
                        .description("product 1 description")
                        .price(100.0)
                        .build()
        );
    }


    @AfterEach
    void tearDown(){
        log.info("Tearing down integration test");
        productRepository.deleteById(1L);
    }




    @Test
    public void getProductByIdExits(){
        ResponseEntity<ProductDTO> response = restTemplate.getForEntity("/api/product/1", ProductDTO.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("product 1", response.getBody().getName());
        Assertions.assertEquals("product 1 description", response.getBody().getDescription());
        Assertions.assertEquals(100.0, response.getBody().getPrice());
    }

    @Test
    public void saveProduct() throws Exception{

        MockMultipartFile file = new MockMultipartFile("file", "image.jpeg", "image/jpeg", "image".getBytes());
        mockMvc.perform(
                multipart(HttpMethod.POST, "/api/create")
                .file(file)
                        .param("id", "2")
                        .param("name", "name 2")
                        .param("description", "description 2")
                        .param("price", "323.2")
                .contentType(MediaType.MULTIPART_FORM_DATA)
        ).andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(2L));
    }

}

