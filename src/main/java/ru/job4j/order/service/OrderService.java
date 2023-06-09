package ru.job4j.order.service;

import ru.job4j.order.domain.Status;
import ru.job4j.order.domain.dto.OrderDTO;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j ORDER
 * OrderService поведение бизнес логики обрабoтки модели Order
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 27.04.2023
 */
public interface OrderService {
    Optional<OrderDTO> create(OrderDTO orderDTO);
    Optional<OrderDTO> findOrderById(int orderId);
    boolean update(OrderDTO orderDTO);
    boolean delete(int orderId);
    Optional<Status> getStatusByOrderId(int orderId);
    boolean setStatusByOrderId(int orderId, int statusId);
}
