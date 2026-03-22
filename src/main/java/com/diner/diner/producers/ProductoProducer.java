package com.diner.diner.producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ProductoProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarProducto(Object productoEvent) {

        kafkaTemplate.send("productos", productoEvent);
        log.info("Producto enviado a Kafka: {}", productoEvent);

    }

}
