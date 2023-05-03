package ru.job4j.order.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j ORDER
 * OrderDTO DTO модель для отображение заказа и его компонентов.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 03.05.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int id;
    private String name;
    private String description;
    private int statusId;
    private String status;
    private int dishId;
    private String dishName;
}
