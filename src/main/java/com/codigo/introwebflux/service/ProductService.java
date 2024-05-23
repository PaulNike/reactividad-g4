package com.codigo.introwebflux.service;

import com.codigo.introwebflux.dao.ProductRepository;
import com.codigo.introwebflux.entity.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final Sinks.Many<Product> sinks = Sinks.many().multicast().onBackpressureBuffer();

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Mono<Product> getProductById(Long id){
        return productRepository.findById(id);
    }
    public Mono<Product> createProduct(Product product){
        return productRepository.save(product).doOnSuccess(sinks::tryEmitNext);
    }
    public Mono<Void> deleteProduct(Long id){
        return  productRepository.deleteById(id);
    }

    public Flux<Product> getAllProducts2(){
        Mono<Void> logOperation = Mono.fromRunnable( () -> {
            System.out.println("LOG: CONSULTANDO PRODUCTOS ...");
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        return  logOperation.thenMany(productRepository.findAll());
    }
    public Flux<Product> getChanges(){
        return sinks.asFlux();
    }
}
