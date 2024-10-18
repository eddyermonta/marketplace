package com.nexsys.marketplace.service.category;

import com.nexsys.marketplace.dto.get.CategoryResponseDTO;
import com.nexsys.marketplace.exception.CustomNotFoundException;
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
    public Mono<List<CategoryResponseDTO>> getAllCategories() {
        return platziApiClient.getAllCategories()
                .flatMapMany(Flux::fromArray)
                .collectList()
                .switchIfEmpty(Mono.error(new CustomNotFoundException("No se encontraron categorías."))); // Manejo de errores
    }
}
