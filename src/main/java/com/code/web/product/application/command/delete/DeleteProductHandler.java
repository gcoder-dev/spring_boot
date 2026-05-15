package com.code.web.product.application.command.delete;

import com.code.web.common.mediator.RequestHandler;
import com.code.web.product.domain.port.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteProductHandler implements RequestHandler<DeleteProductRequest, Void> {

    private final ProductRepository productRepository;


    @Override
    public Void handler(DeleteProductRequest request) {
        log.info("Deleting product with id: {}",request.getId());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        productRepository.deleteById(request.getId());

        System.out.println("Producto eliminado");

        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
}
