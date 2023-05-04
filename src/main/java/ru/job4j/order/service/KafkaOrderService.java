package ru.job4j.order.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
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
public class KafkaOrderService implements KafkaService<Integer, OrderDTO> {
    private final KafkaTemplate<Integer, OrderDTO> kafkaTemplate;


    @Override
    public void sendMessage(String topic, Integer key, OrderDTO type) {
        var future = kafkaTemplate.send(topic, key, type);
        future.addCallback(o -> log.debug("SUCCESS message, key: {}, type: {}", key, type),
                o -> log.error("FAILURE message, key: {}, type: {}", key, type));
        kafkaTemplate.flush();
    }

    @Override
    public OrderDTO receive(ConsumerRecord<Integer, OrderDTO> record) {
        var partition = record.partition();
        var keyMessage = record.key();
        var value = record.value();
        log.debug("Partition: {}", partition);
        log.debug("Key: {}", keyMessage);
        log.debug("Value: {}", value);
        return value;
    }
}
