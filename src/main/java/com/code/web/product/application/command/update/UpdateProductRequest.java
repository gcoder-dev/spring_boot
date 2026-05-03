package com.code.web.product.application.command.update;

import org.springframework.web.multipart.MultipartFile;

import com.code.web.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateProductRequest implements Request<Void> {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private MultipartFile file;
}
