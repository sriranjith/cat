package com.sri.cat.message.rabbitMQ;

import com.sri.cat.redis.Coffee;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component
public class Receiver {

    AtomicInteger key = new AtomicInteger();

    private final ReactiveRedisConnectionFactory factory;
    private final ReactiveRedisOperations<String, Coffee> coffeeOps;

    public Receiver(ReactiveRedisConnectionFactory factory, ReactiveRedisOperations<String,
            Coffee> coffeeOps) {
        this.factory = factory;
        this.coffeeOps = coffeeOps;
    }

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        factory.getReactiveConnection().serverCommands().flushAll().
                thenMany(Flux.just(message).map(name -> new Coffee(UUID.randomUUID().toString(), name)).flatMap(coffee -> coffeeOps.opsForValue().set(message, coffee))).thenMany(coffeeOps.keys("*").flatMap(coffeeOps.opsForValue()::get)).subscribe(System.out::println);
        System.out.println("Done");
    }

}