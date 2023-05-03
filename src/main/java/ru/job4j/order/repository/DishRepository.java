package ru.job4j.order.repository;

import ru.job4j.order.domain.Dish;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j ORDER
 * DishRepository интерфейс описывает поведение для загрузки модели Dish
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 03.05.2023
 */
public interface DishRepository {
    Optional<Dish> findById(int dishId);
}
