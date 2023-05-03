package ru.job4j.order.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import ru.job4j.order.domain.Dish;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j ORDER
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 03.05.2023
 */
@Repository
@Slf4j
public class DishAPIRepository implements DishRepository {

    private final RestTemplate restClient;

    @Value("${url-api.dish}")
    private String urlApiDish;

    public DishAPIRepository(RestTemplate restClient) {
        this.restClient = restClient;
    }

    @Override
    public Optional<Dish> findById(int dishId) {
        String url = String.format("%s/%d", urlApiDish, dishId);
        try {
            Dish dish = restClient.getForObject(
                    url,
                    Dish.class
            );
            return Optional.ofNullable(dish);
        } catch (Exception exception) {
            log.error("Error rest Client: {}", exception);
            return Optional.empty();
        }
    }
}
