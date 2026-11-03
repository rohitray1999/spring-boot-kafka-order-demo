package com.example.kafkademo.consumer;

import com.example.kafkademo.model.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    @KafkaListener(topics = "order-created")
    public void consume(OrderEvent event) {
        System.out.println("Received order event: " + event);
        // Add business processing here (e.g. notify another service).
    }
}
