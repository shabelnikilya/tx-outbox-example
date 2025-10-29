package org.example.outbox.order.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.outbox.order.kafka.KafkaProducerService;
import org.example.outbox.order.repository.OutboxEventRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class OutboxProcessingScheduler {

    private final OutboxEventRepository repository;
    private final KafkaProducerService producerService;

    // по хорошему scheduler lock
    @Scheduled(fixedRateString = "5000")
    public void process() {
        log.info("Start scheduler");

        var events = repository.findByEventType("CREATED");

        events.forEach(event -> {
            var payload = event.getPayload();

            try {
                log.info("Start send: {}", payload);
                producerService.send(payload);
                repository.deleteById(event.getId());
            } catch (Exception e) {
                log.error("Error send message to kafka: {}", payload);
            }
        });
    }
}
