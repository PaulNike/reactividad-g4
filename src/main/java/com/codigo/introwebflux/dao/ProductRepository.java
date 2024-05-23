package com.codigo.introwebflux.dao;

import com.codigo.introwebflux.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product,Long> {

}
