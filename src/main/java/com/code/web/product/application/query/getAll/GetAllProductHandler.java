package com.code.web.product.application.query.getAll;

import com.code.web.common.mediator.RequestHandler;
import com.code.web.product.domain.entity.Product;
import com.code.web.product.domain.port.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllProductHandler implements RequestHandler<GetAllProductRequest, GetAllProductResponse> {

    private final ProductRepository productRepository;

    @Override
    public GetAllProductResponse handler(GetAllProductRequest request) {
        List<Product> products = productRepository.findAll();
        return new GetAllProductResponse(products);
    }

    @Override
    public Class<GetAllProductRequest> getRequestType() {
        return GetAllProductRequest.class;
    }
}
