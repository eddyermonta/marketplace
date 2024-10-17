package com.nexsys.marketplace.service.product;

import com.nexsys.marketplace.dto.ProductDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductService {
    Mono<List<ProductDTO>> getAllProducts();
}
