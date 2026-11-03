package com.example.kafkademo.controller;

import com.example.kafkademo.model.OrderEvent;
import com.example.kafkademo.producer.OrderProducer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.concurrent.CompletionException;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }


    //ROHIT KUMAR RAY
    @PostMapping
    public ResponseEntity<Map<String, String>> createOrder(@Valid @RequestBody OrderEvent event) {
        try {
            orderProducer.sendOrder(event).join();
            return ResponseEntity.accepted().body(Map.of(
                    "message", "Order event published to Kafka",
                    "orderId", event.getOrderId()
            ));
        } catch (CompletionException ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Could not publish order event to Kafka", ex.getCause());
        }
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "kafka-demo");
    }
}
