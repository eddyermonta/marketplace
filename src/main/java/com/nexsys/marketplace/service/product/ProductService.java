package com.nexsys.marketplace.service.product;

import com.nexsys.marketplace.dto.get.ProductResponseDTO;
import com.nexsys.marketplace.dto.post.CreateProductRequestDTO;
import com.nexsys.marketplace.dto.post.CreateProductResponseDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductService {
    Mono<List<ProductResponseDTO>> getAllProducts();
    Mono<CreateProductResponseDTO> createProduct(CreateProductRequestDTO productRequestDTO);
}
