package com.jmc.webflux.controller;

import com.jmc.lang.Threads;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RestController
@Slf4j
public class WebFluxController {
    @GetMapping("/sync")
    public String sync() throws InterruptedException {
        log.info("sync开始");
        TimeUnit.SECONDS.sleep(5);
        log.info("sync结束");
        return "sync-ok";
    }

    /**
     * Mono可以放0 ~ 1个对象
     */
    @GetMapping("/asyncMono")
    public Mono<String> asyncMono() {
        log.info("asyncMono开始");
        var res = Mono.fromSupplier(() -> {
            Threads.sleep(TimeUnit.SECONDS, 5);
            return "asyncMono-ok";
        });
        log.info("asyncMono结束");
        return res;
    }

    /**
     * Flux可以放多个对象，并用SSE进行流式传输
     */
    @GetMapping(value = "/asyncFlux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> asyncFlux() {
        log.info("asyncFlux开始");
        // 打印1 ~ 5，打印间隔为1s
        var res = Flux.fromStream(IntStream.rangeClosed(1, 5).boxed())
                .delayElements(Duration.ofSeconds(1));
        log.info("asyncFlux结束");
        return res;
    }
}
