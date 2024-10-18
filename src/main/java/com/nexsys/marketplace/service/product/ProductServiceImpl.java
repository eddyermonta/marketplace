package com.nexsys.marketplace.service.product;


import com.nexsys.marketplace.dto.get.ProductResponseDTO;
import com.nexsys.marketplace.dto.post.CreateProductRequestDTO;
import com.nexsys.marketplace.dto.post.CreateProductResponseDTO;
import com.nexsys.marketplace.repository.PlatziApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private final PlatziApiClient platziApiClient;
    @Autowired
    public ProductServiceImpl(PlatziApiClient platziApiClient) {
        this.platziApiClient = platziApiClient;
    }

    @Override
    public Mono<List<ProductResponseDTO>> getAllProducts() {
        return platziApiClient.getAllProducts()
                .flatMapMany(Flux::fromArray)
                .collectList();
    }

    @Override
    public Mono<CreateProductResponseDTO> createProduct(CreateProductRequestDTO productRequestDTO) {
        return platziApiClient.createProduct(productRequestDTO)
                .flatMap(response ->
                        Mono.just(
                                new CreateProductResponseDTO(response.pid()
                                )
                        )
                );
    }
}
