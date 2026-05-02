package com.code.web.product.application.command.create;

import com.code.web.common.mediator.RequestHandler;
import com.code.web.product.domain.entity.Product;
import com.code.web.product.domain.port.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductHandler implements RequestHandler<CreateProductRequest, Void> {

    private final ProductRepository productRepository;


    @Override
    public Void handler(CreateProductRequest request) {
        Product product = Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(request.getImage())
                .build();

        productRepository.upsert(product);
        return null;
    }

    @Override
    public Class<CreateProductRequest> getRequestType() {
        return CreateProductRequest.class;
    }
}
