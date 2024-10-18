package com.nexsys.marketplace.service.product;


import com.nexsys.marketplace.domain.Category;
import com.nexsys.marketplace.domain.Product;
import com.nexsys.marketplace.dto.get.CategoryResponseDTO;
import com.nexsys.marketplace.dto.get.ProductResponseDTO;
import com.nexsys.marketplace.dto.post.CreateProductRequestDTO;
import com.nexsys.marketplace.dto.post.CreateProductResponseDTO;
import com.nexsys.marketplace.repository.CategoryRepository;
import com.nexsys.marketplace.repository.PlatziApiClient;
import com.nexsys.marketplace.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final PlatziApiClient platziApiClient;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(PlatziApiClient platziApiClient, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.platziApiClient = platziApiClient;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Mono<List<ProductResponseDTO>> getAllProducts() {
        return platziApiClient.getAllProducts()
                .flatMapMany(Flux::fromArray)
                .collectList();
    }

    @Override
    public Mono<CreateProductResponseDTO> createProduct(CreateProductRequestDTO productRequestDTO) {
        return fetchCategoryFromApi(productRequestDTO.categoryId())
                .flatMap(category -> createAndSaveProduct(productRequestDTO, category));
    }

    private Mono<Category> fetchCategoryFromApi(Long categoryId) {
        return platziApiClient.getAllCategories()
                .flatMapMany(Flux::fromArray)
                .filter(categoryResponse -> categoryResponse.cId().equals(categoryId))
                .singleOrEmpty()
                .flatMap(this::findOrCreateCategory)
                .switchIfEmpty(Mono.error(new RuntimeException("Categoría no encontrada en la API."))); // Manejo de errores
    }

    // Método para buscar o crear la categoría en la base de datos
    private Mono<Category> findOrCreateCategory(CategoryResponseDTO categoryResponse) {
        return categoryRepository.findById(categoryResponse.cId())
                .map(Mono::just) // Convierte el Optional a Mono si existe
                .orElseGet(() -> createCategory(categoryResponse)); // Si no existe, crea una nueva categoría
    }

    // Método para crear una nueva categoría en la base de datos
    private Mono<Category> createCategory(CategoryResponseDTO categoryResponse) {
        Category category = mapToCategory(categoryResponse);
        return Mono.fromCallable(() -> categoryRepository.save(category))
                .subscribeOn(Schedulers.boundedElastic()); // Asegura la ejecución asíncrona
    }

    // Método para crear y guardar el producto
    private Mono<CreateProductResponseDTO> createAndSaveProduct(CreateProductRequestDTO productRequestDTO, Category category) {
        Product product = mapToProduct(productRequestDTO, category);
        return saveProduct(product);
    }


    // Método para guardar el producto
    private Mono<CreateProductResponseDTO> saveProduct(Product product) {
        return Mono.fromCallable(() -> productRepository.save(product))
                .subscribeOn(Schedulers.boundedElastic())
                .map(savedProduct -> new CreateProductResponseDTO(savedProduct.getPId()));
    }

    private Category mapToCategory(CategoryResponseDTO categoryResponseDTO) {
        return new Category(categoryResponseDTO.cId(), categoryResponseDTO.title());
    }

    // Método para mapear el DTO al objeto Product
    private Product mapToProduct(CreateProductRequestDTO request, Category category) {
        Product product = new Product();
        product.setName(request.name()); // Usa los datos de la respuesta
        product.setBuyPrice(request.priceFinal()); // Mapea el precio
        product.setCategory(category); // Asigna la categoría encontrada
        product.setImages(request.imageUrl()); // Toma la lista de imágenes
        return product;
    }
}

