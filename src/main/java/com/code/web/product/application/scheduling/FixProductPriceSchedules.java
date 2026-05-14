package com.code.web.product.application.scheduling;

import com.code.web.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FixProductPriceSchedules {

    private final ProductRepository productRepository;


    @Scheduled(fixedRate = 5000)
    public void fixProductPrice(){
        log.info("Fixing product price");

        productRepository.findAll().forEach( product -> {
            product.setPrice(product.getPrice() * 1.1);
            productRepository.upsert(product);
        });

        log.info("Finished fixing product prices");
    }
}
