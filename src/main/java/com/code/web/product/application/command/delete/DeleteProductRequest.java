package com.code.web.product.application.command.delete;

import com.code.web.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DeleteProductRequest implements Request<Void> {
    private Long id;
}
