package com.codigo.introwebflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MonoFluxController {

    @GetMapping("/mono")
    public Mono<String> getMono(){
        //Creamos un fujo de datos de tipo Mono, y le brindamos los valores.
        Mono<String> singleItem = Mono.just("Hello, Mono");
        return singleItem;
    }

    @GetMapping("/flux")
    public Flux<String> getFlux(){
        Flux<String> multipleItems = Flux.just("Hello","Flux","World");
        return multipleItems;
    }

    @GetMapping("/flux/map")
    public Flux<Integer> getMappedFlux(){
        //Aqui creamos un flujo de datos, en este caso numero del 1 al 5
        Flux<Integer> numbers = Flux.just(1,2,3,4,5);
        // map(number -> number * 2) aplicamos una función a cada numero que lo multiplica X 2
        Flux<Integer> squareNumbers = numbers.map(number -> number * 2);
        //Resultado seria 2,4,6,8,10
        //retornamos el resultado del MAP
        return squareNumbers;
    }

    @GetMapping("/flux/filter")
    public  Flux<Integer> getFilteredFlux(){
        //Aqui creamos un flujo de datos, en este caso numero del 1 al 5
        Flux<Integer> numbers = Flux.just(1,2,3,4,5);
        // filter(num -> num % 2 == 0) Filtramos los numerto, emitiendo solo los que sean pares
        Flux<Integer> result = numbers.filter(num -> num % 2 == 0);
        //Resultado seria 2,4
        //retornamos el resultado
        return result;
    }

    @GetMapping("/flux/flatmap")
    public Flux<String> getFlatMappedFlux(){
        //Aqui creamos un flujo de datos, en este caso letras A, B, C
        Flux<String> letters = Flux.just("A","B","C");
        //flatMap(letter -> Flux.just(letter + "1", letter + "2")) transformar cada letra en una nuevo flujo de 2 elementos
        Flux<String> expandedLetters = letters.flatMap(letter -> Flux.just(letter + "1", letter + "2"));
        //Resultado seria A1A2B1B2C1C2
        //retornamos el resultado
        return expandedLetters;
    }

    @GetMapping("/flux/reduce")
    public Mono<Integer> getReducedFlux(){
        Flux<Integer> numbers = Flux.just(1,2,3,4,5);
        Mono<Integer> sun = numbers.reduce((acc, number) -> acc + number);
        return sun;
    }

}
