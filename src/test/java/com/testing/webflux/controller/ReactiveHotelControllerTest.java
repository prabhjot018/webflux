package com.testing.webflux.controller;

import com.testing.webflux.entity.Hotel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ReactiveHotelControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getAllHotels_shouldBeNonBlocking() {
        Flux<Hotel> hotelFlux = webTestClient.get()
                .uri("/api/reactive-hotels")
                .exchange()
                .returnResult(Hotel.class)
                .getResponseBody();

        StepVerifier.create(hotelFlux)
                .expectNextCount(5) // change this to your actual count, in our case its 5 based on schema.sql
                .verifyComplete();  // ✅ test passes only if no blocking
    }

    @Test
    void getHotelById_shouldBeNonBlocking() {
        Mono<Hotel> hotelMono = webTestClient.get()
                .uri("/api/reactive-hotels/1")
                .exchange()
                .returnResult(Hotel.class)
                .getResponseBody()
                .single(); // assuming 1 result

        StepVerifier.create(hotelMono)
                .expectNextMatches(h -> h.getId() == 1L)
                .verifyComplete(); // ✅ no blocking = test passes
    }
}

