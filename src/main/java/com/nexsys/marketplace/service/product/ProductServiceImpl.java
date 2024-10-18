package com.nexsys.marketplace.service.product;


import com.nexsys.marketplace.domain.Category;
import com.nexsys.marketplace.domain.Product;
import com.nexsys.marketplace.domain.ProductImage;
import com.nexsys.marketplace.dto.get.CategoryResponseDTO;
import com.nexsys.marketplace.dto.get.ProductResponseDTO;
import com.nexsys.marketplace.dto.post.CreateProductRequestDTO;
import com.nexsys.marketplace.dto.post.CreateProductResponseDTO;
import com.nexsys.marketplace.exception.CustomNotFoundException;
import com.nexsys.marketplace.repository.CategoryRepository;
import com.nexsys.marketplace.repository.PlatziApiClient;
import com.nexsys.marketplace.repository.ProductImageRepository;
import com.nexsys.marketplace.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final PlatziApiClient platziApiClient;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository; // Asegúrate de que este repositorio esté inyectado

    @Autowired
    public ProductServiceImpl(PlatziApiClient platziApiClient, ProductRepository productRepository, CategoryRepository categoryRepository, ProductImageRepository productImageRepository) {
        this.platziApiClient = platziApiClient;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productImageRepository = productImageRepository; // Inicializa el repositorio
    }

    @Override
    public Mono<List<ProductResponseDTO>> getAllProducts() {
        return platziApiClient.getAllProducts()
                .flatMapMany(Flux::fromArray)
                .collectList()
                .switchIfEmpty(Mono.error(new CustomNotFoundException("No se encontraron productos."))); // Manejo de errores
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
                .switchIfEmpty(Mono.error(new CustomNotFoundException("Categoría no encontrada en la API."))); // Manejo de errores
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

    // Ajusta este método para retornar Mono<CreateProductResponseDTO>
    private Mono<CreateProductResponseDTO> createAndSaveProduct(CreateProductRequestDTO productRequestDTO, Category category) {
        Product product = mapToProduct(productRequestDTO, category);
        return saveProduct(product)
                .flatMap(savedProduct -> {
                    // Guarda las imágenes y retorna el DTO del producto
                    return saveProductImages(savedProduct, productRequestDTO.imageUrl())
                            .then(Mono.just(new CreateProductResponseDTO(savedProduct.getPId()))); // Retorna el CreateProductResponseDTO
                });
    }

    // Método para guardar el producto
    private Mono<Product> saveProduct(Product product) {
        return Mono.fromCallable(() -> productRepository.save(product))
                .subscribeOn(Schedulers.boundedElastic())
                .map(savedProduct -> {
                    // Mapea la respuesta
                    return savedProduct;
                });
    }

    // Este método guarda las imágenes y ahora no necesita devolver un Mono<Void>
    private Mono<Void> saveProductImages(Product product, List<String> images) {
        List<ProductImage> productImages = images.stream()
                .map(imageUrl -> new ProductImage(product, imageUrl))
                .collect(Collectors.toList());
        return Mono.fromRunnable(() -> {
            // Guarda las imágenes en la base de datos
            productImageRepository.saveAll(productImages);
        }).subscribeOn(Schedulers.boundedElastic()).then();
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
        // Elimina la línea de imágenes, ya que se manejará en saveProductImages
        return product;
    }
}

