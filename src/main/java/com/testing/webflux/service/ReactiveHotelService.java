package com.testing.webflux.service;

import com.testing.webflux.entity.Hotel;
import com.testing.webflux.repository.ReactiveHotelRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveHotelService {
    private final ReactiveHotelRepository repository;

    public ReactiveHotelService(ReactiveHotelRepository repository) {
        this.repository = repository;
    }

    public Mono<Hotel> findById(Long id) {
        return repository.findById(id);
    }

    public Flux<Hotel> findAll() {
        return repository.findAll();
    }
}
