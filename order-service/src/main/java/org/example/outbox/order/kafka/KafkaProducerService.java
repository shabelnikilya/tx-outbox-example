package org.example.outbox.order.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final static String topic = "sendOutbox";

    private final StreamBridge streamBridge;


    public void send(String message) {
        streamBridge.send(topic, message);
    }
}
