package com.nexsys.marketplace.service.product;


import com.nexsys.marketplace.dto.ProductDTO;
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
    public Mono<List<ProductDTO>> getAllProducts() {
        return platziApiClient.getAllProducts()
                .flatMapMany(Flux::fromArray)
                .collectList();
    }
}
