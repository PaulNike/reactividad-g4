package com.codigo.introwebflux.dao;

import com.codigo.introwebflux.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product,Long> {
}
