package com.nexsys.marketplace.service.category;

import com.nexsys.marketplace.dto.CategoryDTO;
import com.nexsys.marketplace.repository.PlatziApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final PlatziApiClient platziApiClient;

    @Autowired
    public CategoryServiceImpl (PlatziApiClient platziApiClient) {
        this.platziApiClient = platziApiClient;
    }


    @Override
    public Mono<List<CategoryDTO>> getAllCategories() {
        return platziApiClient.getAllCategories()
                .flatMapMany(Flux::fromArray)
                .map(category -> {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    categoryDTO.setCId(category.getCId());
                    categoryDTO.setTitle(category.getTitle());
                    return categoryDTO;
                })
                .collectList();
    }
}
