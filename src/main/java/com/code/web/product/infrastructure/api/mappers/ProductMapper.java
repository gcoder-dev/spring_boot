package com.code.web.product.infrastructure.api.mappers;

import com.code.web.product.application.command.create.CreateProductRequest;
import com.code.web.product.application.command.update.UpdateProductRequest;
import com.code.web.product.domain.entity.Product;
import com.code.web.product.infrastructure.api.dtos.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {
    //@Mapping(target = "id", source = "productId", ignore = true)
    CreateProductRequest mapToCreateProductRequest(ProductDTO productDTO);

    UpdateProductRequest mapToUpdateProductRequest(ProductDTO productDTO);


    ProductDTO mapToProductDTO(Product product);
}
