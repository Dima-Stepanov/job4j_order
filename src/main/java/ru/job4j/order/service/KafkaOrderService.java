package ru.job4j.order.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.order.domain.dto.OrderDTO;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j ORDER
 * KafkaOrderService реализация обмена сообщениями через KAFKA.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.05.2023
 */
@Service
@AllArgsConstructor
@Slf4j
public class KafkaOrderService implements KafkaService<String, String, OrderDTO> {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void sendMessage(String topic, String key, OrderDTO type) {
        var future = kafkaTemplate.send(topic, key, type);
        future.addCallback(o -> log.debug("SUCCESS message, key: {}, type: {}", key, type),
                o -> log.error("FAILURE message, key: {}, type: {}", key, type));
        kafkaTemplate.flush();
    }

    @Override
    public OrderDTO receive(ConsumerRecord<String, String> record) {
        log.debug("Partition: {}", record.partition());
        log.debug("Key: {}", record.key());
        log.debug("Value: {}", record.value());
        OrderDTO orderDTO;
        try {
            orderDTO = objectMapper.readValue(record.value(), OrderDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return orderDTO;
    }
}
