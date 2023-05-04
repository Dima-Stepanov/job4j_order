package ru.job4j.order.mapper;

import org.springframework.stereotype.Component;
import ru.job4j.order.domain.Dish;
import ru.job4j.order.domain.Order;
import ru.job4j.order.domain.Status;
import ru.job4j.order.domain.dto.OrderDTO;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j ORDER
 * OrderMapper класс для преобразования DAO Order в DTO Order и наоборот.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 03.05.2023
 */
@Component
public class OrderMapper {
    /**
     * Метод собирает OrderDTO из DAO моделей Order и Dish
     *
     * @param order Order
     * @param dish  Dish
     * @return OrderDTO
     */
    public OrderDTO getOrderDtoByOrderAndDish(Order order, Dish dish) {
        return new OrderDTO(order.getId(),
                order.getName(),
                order.getDescription(),
                order.getStatus().getId(),
                order.getStatus().getName(),
                dish.getId(),
                dish.getName());
    }

    /**
     * Метод собирает OrderDTO из DAO моделей Order
     *
     * @param order Order
     * @return OrderDTO
     */
    public OrderDTO getOrderDTOByOrder(Order order) {
        return new OrderDTO(order.getId(),
                order.getName(),
                order.getDescription(),
                order.getStatus().getId(),
                order.getStatus().getName(),
                order.getDishId(),
                null);
    }

    /**
     * Метод собирает DAO Order из OrderDTO
     *
     * @param orderDTO OrderDTO
     * @return DAO Order
     */
    public Order getOrderByOrderDTO(OrderDTO orderDTO) {
        return new Order(orderDTO.getId(),
                orderDTO.getName(),
                orderDTO.getDescription(),
                new Status(orderDTO.getStatusId(), orderDTO.getStatus()),
                orderDTO.getDishId());
    }
}

