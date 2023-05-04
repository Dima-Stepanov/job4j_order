package ru.job4j.order.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.order.domain.Dish;
import ru.job4j.order.domain.Order;
import ru.job4j.order.domain.Status;
import ru.job4j.order.domain.dto.OrderDTO;
import ru.job4j.order.mapper.OrderMapper;
import ru.job4j.order.repository.DishRepository;
import ru.job4j.order.repository.OrderRepository;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j ORDER
 * 3. Spring boot + Kafka [#505039]
 * SimpleOrderService бизнес логика обработки модели Order
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 27.04.2023
 */
@Service
@AllArgsConstructor
@Slf4j
public class SimpleOrderService implements OrderService {
    private final KafkaOrderService kafkaOrderService;
    private static final String TOPIC_ORDERS = "job4j_orders";
    private static final String TOPIC_NOTIFICATION = "job4j_notifications";
    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final OrderMapper orderMapper;

    @Override
    public Optional<OrderDTO> create(OrderDTO orderDTO) {
        try {
            var order = orderMapper.getOrderByOrderDTO(orderDTO);
            orderRepository.save(order);
            orderDTO.setId(order.getId());
         //   kafkaOrderService.sendMessage(TOPIC_ORDERS, orderDTO.getId(), orderDTO);
            kafkaOrderService.sendMessage(TOPIC_NOTIFICATION, orderDTO.getId(), orderDTO);
            return Optional.of(orderDTO);
        } catch (Exception e) {
            log.error("Fail create order: {}, error: {}", orderDTO, e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<OrderDTO> findOrderById(int orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            return Optional.empty();
        }
        Optional<Dish> dish = dishRepository.findById(order.get().getDishId());
        if (dish.isEmpty()) {
            dish = Optional.of(new Dish(-1, "dishEmpty", ""));
        }
        OrderDTO orderDTO = orderMapper.getOrderDtoByOrderAndDish(order.get(), dish.get());
        return Optional.of(orderDTO);
    }

    @Override
    public boolean update(OrderDTO orderDTO) {
        var order = orderMapper.getOrderByOrderDTO(orderDTO);
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
        return orderRepository.findById(orderId).map(Order::getStatus);
    }
}
