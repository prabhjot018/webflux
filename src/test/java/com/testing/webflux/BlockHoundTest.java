package com.testing.webflux;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BlockHoundTest {

    @BeforeAll
    static void setup() {
        BlockHound.install();
    }

    @Test
    void shouldDetectBlockingCall() {
        assertThrows(Exception.class, () -> {
            Mono.fromCallable(() -> {
                        Thread.sleep(10);
                        return "I was a blocking call";
                    })
                    .subscribeOn(Schedulers.parallel())
                    .block();
        });
    }
}
