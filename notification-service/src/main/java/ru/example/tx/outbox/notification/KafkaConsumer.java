package ru.example.tx.outbox.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class KafkaConsumer {


    @Bean
    public Consumer<String> receiveMsgOutbox() {
        return msg -> {
            log.info("Message receive in notification service: {}", msg);
        };
    }
}
