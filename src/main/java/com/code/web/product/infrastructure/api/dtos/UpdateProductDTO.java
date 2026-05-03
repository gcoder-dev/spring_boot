package com.code.web.product.infrastructure.api.dtos;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateProductDTO {
    private Long id;
    @NotBlank
    private String name;
    @Length(min = 10, max = 255)
    private String description;
    @DecimalMin(value = "0.2", inclusive = false)
    @DecimalMax(value = "3333.34", inclusive = false)
    private Double price;
    private MultipartFile image;
}
