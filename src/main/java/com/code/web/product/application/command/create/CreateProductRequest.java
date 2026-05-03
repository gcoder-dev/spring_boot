package com.code.web.product.application.command.create;

import org.springframework.web.multipart.MultipartFile;

import com.code.web.common.mediator.Request;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateProductRequest implements Request<Void> {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private MultipartFile file;

}
