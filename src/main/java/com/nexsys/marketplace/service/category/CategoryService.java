package com.nexsys.marketplace.service.category;

import com.nexsys.marketplace.dto.CategoryDTO;
import reactor.core.publisher.Mono;
import java.util.List;

public interface CategoryService {
    Mono<List<CategoryDTO>> getAllCategories();
}
