package com.nexsys.marketplace.repository;

import com.nexsys.marketplace.dto.get.CategoryResponseDTO;
import com.nexsys.marketplace.dto.get.ProductResponseDTO;
import com.nexsys.marketplace.dto.post.CreateProductRequestDTO;
import com.nexsys.marketplace.dto.post.CreateProductResponseDTO;
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

    public Mono<ProductResponseDTO[]> getAllProducts(){
        return this.webClient.get()
                .uri(Constants.PLATZI_API_PRODUCTS)
                .retrieve()
                .bodyToMono(ProductResponseDTO[].class);
    }

    public Mono<CategoryResponseDTO[]> getAllCategories() {
        return this.webClient.get()
                .uri(Constants.PLATZI_API_CATEGORIES)
                .retrieve()
                .bodyToMono(CategoryResponseDTO[].class);
    }

    public Mono<CreateProductResponseDTO> createProduct(CreateProductRequestDTO productApiRequestDTO) {
        return this.webClient.post()
                .uri(Constants.PLATZI_CREATE_PRODUCT)
                .bodyValue(productApiRequestDTO)  // Enviar el cuerpo de la solicitud mapeada
                .retrieve()
                .bodyToMono(CreateProductResponseDTO.class);
    }
}
