package com.sri.cat.message.rabbitMQ;


import com.sri.cat.redis.Coffee;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class RabbitController {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    AtomicInteger key = new AtomicInteger();

    @Autowired
    public RabbitController(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/rabbit")
    public void all() {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, "foo.bar" + ".baz",
                key.incrementAndGet() + "");
    }
}