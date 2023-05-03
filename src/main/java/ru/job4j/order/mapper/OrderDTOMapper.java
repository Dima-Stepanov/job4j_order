package ru.job4j.order.mapper;

import org.springframework.stereotype.Component;
import ru.job4j.order.domain.Dish;
import ru.job4j.order.domain.Order;
import ru.job4j.order.domain.dto.OrderDTO;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j ORDER
 * OrderDTOMapper класс для преобразования DAO Order в DTO Order
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 03.05.2023
 */
@Component
public class OrderDTOMapper {
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
}
