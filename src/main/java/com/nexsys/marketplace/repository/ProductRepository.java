package com.nexsys.marketplace.repository;

import com.nexsys.marketplace.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
