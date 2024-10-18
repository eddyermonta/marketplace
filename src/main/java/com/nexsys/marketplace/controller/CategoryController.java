package com.nexsys.marketplace.controller;

import com.nexsys.marketplace.dto.get.CategoryResponseDTO;
import com.nexsys.marketplace.service.category.CategoryService;
import com.nexsys.marketplace.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(Constants.NEXSYS_V1)
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(Constants.PLATZI_API_CATEGORIES)
    public Mono<List<CategoryResponseDTO>> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
