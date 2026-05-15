package com.code.web.product.application.query.getAll;

import com.code.web.common.mediator.RequestHandler;
import com.code.web.product.domain.entity.Product;
import com.code.web.product.domain.port.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllProductHandler implements RequestHandler<GetAllProductRequest, GetAllProductResponse> {

    private final ProductRepository productRepository;

    @Override
    public GetAllProductResponse handler(GetAllProductRequest request) {
        log.info("Getting all products");
        List<Product> products = productRepository.findAll();
        log.info("Found {} products",products.size());
        return new GetAllProductResponse(products);
    }

    @Override
    public Class<GetAllProductRequest> getRequestType() {
        return GetAllProductRequest.class;
    }
}
