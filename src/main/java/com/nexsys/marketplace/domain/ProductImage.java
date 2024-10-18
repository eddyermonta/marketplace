package com.nexsys.marketplace.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_p_id", nullable = false)
    private Product product; // Este es el producto asociado
    private String images;

    // Constructor
    public ProductImage(Product product, String imageUrl) {
        this.product = product;
        this.images = imageUrl;
    }

}
