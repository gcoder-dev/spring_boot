package com.code.web.product.application.query.getAll;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import com.code.web.product.domain.entity.Product;

@Data
@AllArgsConstructor
public class GetAllProductResponse {
    private List<Product> products;
}
