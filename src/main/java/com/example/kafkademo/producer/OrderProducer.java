package com.example.kafkademo.producer;

import com.example.kafkademo.model.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderProducer {
    private static final String TOPIC = "order-created";
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<Void> sendOrder(OrderEvent event) {
        return kafkaTemplate.send(TOPIC, event.getOrderId(), event)
                .thenApply(result -> {
                    System.out.printf("Published order %s to topic %s, partition %d, offset %d%n",
                            event.getOrderId(),
                            result.getRecordMetadata().topic(),
                            result.getRecordMetadata().partition(),
                            result.getRecordMetadata().offset());
                    return null;
                });
    }
}
