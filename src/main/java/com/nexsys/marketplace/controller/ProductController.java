package com.nexsys.marketplace.controller;

import com.nexsys.marketplace.dto.get.ProductResponseDTO;
import com.nexsys.marketplace.dto.post.CreateProductRequestDTO;
import com.nexsys.marketplace.dto.post.CreateProductResponseDTO;
import com.nexsys.marketplace.service.product.ProductService;
import com.nexsys.marketplace.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(Constants.NEXSYS_V1)
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(Constants.PLATZI_API_PRODUCTS)
    public Mono<List<ProductResponseDTO>> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping(Constants.API_PRODUCTS_CREATE)
    public Mono<CreateProductResponseDTO> createProduct(@RequestBody CreateProductRequestDTO productRequestDTO) {
        return productService.createProduct(productRequestDTO);
    }
}
