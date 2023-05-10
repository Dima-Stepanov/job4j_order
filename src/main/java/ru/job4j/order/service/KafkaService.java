package ru.job4j.order.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j ORDER
 * KafkaService<K, T> интерфейс для обмена сообщениями Kafka
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.05.2023
 */
public interface KafkaService<K, V, T> {
    /**
     * Отправить сообщение
     *
     * @param topic Queue messages
     * @param key   Key message
     * @param type  Type message
     */
    public void sendMessage(String topic, K key, T type);

    /**
     * Получить сообщение от сервиса.
     *
     * @param record Тело сообщения
     */
    public T receive(ConsumerRecord<K, V> record);
}
