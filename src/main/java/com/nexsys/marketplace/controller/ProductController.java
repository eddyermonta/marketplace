package com.nexsys.marketplace.controller;

import com.nexsys.marketplace.dto.ProductDTO;
import com.nexsys.marketplace.service.product.ProductService;
import com.nexsys.marketplace.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public Mono<List<ProductDTO>> getAllProducts() {
        return productService.getAllProducts();
    }

}
