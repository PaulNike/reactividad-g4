package com.codigo.introwebflux.service;

import com.codigo.introwebflux.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Schedulers;

@Service
public class KafkaPersonaListener {

    private final ProductService productService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaPersonaListener(ProductService productService) {
        this.productService = productService;
    }

    @KafkaListener(topics = "usuarios", groupId = "grupo-usuarios")
    public void escuchar(@Payload String message) {
        try {
            Product product = objectMapper.readValue(message, Product.class);
            productService.createProduct(product)
                    .subscribeOn(Schedulers.boundedElastic())
                    .subscribe(
                            result -> System.out.println("Producto Guardado exitosamente: " + result),
                            error -> System.err.println("Error guardando producto: " + error)
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
