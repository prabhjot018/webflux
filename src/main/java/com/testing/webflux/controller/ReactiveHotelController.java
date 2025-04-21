package com.testing.webflux.controller;

import com.testing.webflux.entity.Hotel;
import com.testing.webflux.service.ReactiveHotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/reactive-hotels")
public class ReactiveHotelController {

    private final ReactiveHotelService service;

    public ReactiveHotelController(ReactiveHotelService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<Hotel> getAllHotels() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Hotel>> getHotel(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
