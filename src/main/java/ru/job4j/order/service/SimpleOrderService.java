package ru.job4j.order.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.order.domain.Order;
import ru.job4j.order.domain.Status;
import ru.job4j.order.repository.OrderRepository;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j ORDER
 * SimpleOrderService бизнес логика обработки модели Order
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 27.04.2023
 */
@Service
@AllArgsConstructor
@Slf4j
public class SimpleOrderService implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Optional<Order> create(Order order) {
        try {
            orderRepository.save(order);
            return Optional.of(order);
        } catch (Exception e) {
            log.error("Fail create order: {}, error: {}", order, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Order> findOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public boolean update(Order order) {
        try {
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            log.error("Fail update order: {}, error: {}", order, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int orderId) {
        try {
            orderRepository.deleteById(orderId);
            return true;
        } catch (Exception e) {
            log.error("Fail delete orderId: {}, error: {}", orderId, e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<Status> getStatusByOrderId(int orderId) {
        return findOrderById(orderId).map(Order::getStatus);
    }
}