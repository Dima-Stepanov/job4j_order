package ru.job4j.order.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.order.domain.Order;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j ORDER
 * OrderRepository хранилище заказов в базе данных.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 27.04.2023
 */
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
