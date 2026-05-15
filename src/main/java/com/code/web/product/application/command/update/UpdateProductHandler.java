package com.code.web.product.application.command.update;

import com.code.web.common.mediator.RequestHandler;
import com.code.web.common.util.FileUtils;
import com.code.web.product.domain.entity.Product;
import com.code.web.product.domain.port.ProductRepository;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest, Void> {
    private final ProductRepository productRepository;
    private final FileUtils fileUtils;

    @Override
    public Void handler(UpdateProductRequest request) {
        log.info("Updating product with id: {}",request.getId());
        MultipartFile file = request.getFile();

        String uniqueFileName = fileUtils.saveProductImage(request.getFile());



        Product product = Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(uniqueFileName)
                .build();

        productRepository.upsert(product);
        log.info("Updated product with id: {}",product.getId());
        return null;
    }

    @Override
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }
}
