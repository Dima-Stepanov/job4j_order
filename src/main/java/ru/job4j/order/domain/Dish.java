package ru.job4j.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j ORDER
 * Dish DAO модель описывающая Блюда из которого состоит заказ
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 03.05.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    private int id;
    private String name;
    private String description;
}
