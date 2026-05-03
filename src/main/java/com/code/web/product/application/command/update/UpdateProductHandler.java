package com.code.web.product.application.command.update;

import com.code.web.common.mediator.RequestHandler;
import com.code.web.product.domain.entity.Product;
import com.code.web.product.domain.port.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest, Void> {
    private final ProductRepository productRepository;

    @Override
    public Void handler(UpdateProductRequest request) {
        Product product = Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();

        productRepository.upsert(product);
        return null;
    }

    @Override
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }
}
