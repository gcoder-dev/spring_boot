package com.code.web.product.application.query.getById;

import com.code.web.product.domain.entity.Product;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetProductByIdResponse {
    private Product product;
}
