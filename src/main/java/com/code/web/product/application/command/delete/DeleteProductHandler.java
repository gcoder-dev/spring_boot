package com.code.web.product.application.command.delete;

import com.code.web.common.mediator.RequestHandler;
import com.code.web.product.domain.port.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductHandler implements RequestHandler<DeleteProductRequest, Void> {

    private final ProductRepository productRepository;


    @Override
    public Void handler(DeleteProductRequest request) {

        System.out.println("Eliminando producto con el id: "+request.getId()+" ...");
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
