package com.nexsys.marketplace.service.category;

import com.nexsys.marketplace.dto.get.CategoryResponseDTO;
import reactor.core.publisher.Mono;
import java.util.List;

public interface CategoryService {
    Mono<List<CategoryResponseDTO>> getAllCategories();
}
