package com.nexsys.marketplace.repository;

import com.nexsys.marketplace.dto.CategoryDTO;
import com.nexsys.marketplace.dto.ProductDTO;
import com.nexsys.marketplace.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import static com.nexsys.marketplace.util.Constants.PLATZI_API_URL;

@Repository
public class PlatziApiClient {
    private final WebClient webClient;

    @Autowired
    public PlatziApiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(PLATZI_API_URL).build();
    }

    public Mono<ProductDTO[]> getAllProducts(){
        return this.webClient.get()
                .uri(Constants.PLATZI_API_PRODUCTS)
                .retrieve()
                .bodyToMono(ProductDTO[].class);
    }

    public Mono<CategoryDTO[]> getAllCategories() {
        return this.webClient.get()
                .uri(Constants.PLATZI_API_CATEGORIES)
                .retrieve()
                .bodyToMono(CategoryDTO[].class);
    }
}
