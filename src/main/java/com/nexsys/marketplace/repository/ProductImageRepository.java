package com.nexsys.marketplace.repository;

import com.nexsys.marketplace.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
