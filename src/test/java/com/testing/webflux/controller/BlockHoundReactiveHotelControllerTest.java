package com.testing.webflux.controller;

import com.testing.webflux.entity.Hotel;
import com.testing.webflux.service.ReactiveHotelService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlockHoundReactiveHotelControllerTest {

    @Mock
    private ReactiveHotelService hotelService;

    @InjectMocks
    private ReactiveHotelController hotelController;

    private WebTestClient webTestClient;

    @BeforeAll
    static void setupBlockHound() {
        // BlockHound setup to check for blocking calls
        BlockHound.install();
    }

    @BeforeEach
    void setUp() {
        // Initialize WebTestClient for testing controller
        webTestClient = WebTestClient.bindToController(hotelController).build();
    }

    @Test
    void getHotelById_shouldReturnHotel() {
        // Simulate the `hotelService.findById(1)` returning a Hotel with id=1
        Mono<Hotel> hotelMono = Mono.just(new Hotel(1L, "Test Hotel", 12.34, 56.78));
        when(hotelService.findById(1L)).thenReturn(hotelMono);

        // Make the request and verify the response
        webTestClient.get()
                .uri("/api/reactive-hotels/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Hotel.class)
                .value(hotel -> {
                    assert hotel.getId() == 1L;
                    assert "Test Hotel".equals(hotel.getName());
                });
    }

    @Test
    void getHotelById_shouldDetectBlockingCall() {
        // Simulate a blocking call using Mono.fromCallable or any blocking method
        Mono<Hotel> hotelMono = Mono.fromCallable(() -> {
            // Simulating a blocking call (e.g., accessing a database in a blocking way)
            try {
                Thread.sleep(100); // Simulate blocking operation
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Hotel(1L, "Test Hotel", 12.34, 56.78);
        });

        // Mocking the service to simulate a blocking call in findById()
        when(hotelService.findById(1L)).thenReturn(hotelMono);

        // Expect a blocking call to be detected by BlockHound
        webTestClient.get()
                .uri("/api/reactive-hotels/1")
                .exchange()
                .expectStatus().is5xxServerError();
    }
}
