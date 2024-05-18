package com.codigo.introwebflux.controller;

import com.codigo.introwebflux.entity.Product;
import com.codigo.introwebflux.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Flux<Product> getAllProducts(){
        return productService.getAllProducts2();
    }
    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @PostMapping
    public Mono<Product> createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
}
