package com.testing.webflux.repository;

import com.testing.webflux.entity.Hotel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReactiveHotelRepository extends ReactiveCrudRepository<Hotel, Long> {
}
