package ru.job4j.order.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.order.domain.Order;
import ru.job4j.order.domain.Status;
import ru.job4j.order.domain.dto.OrderDTO;
import ru.job4j.order.service.SimpleOrderService;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j ORDER
 * OrderController REST API модели ORDER
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 27.04.2023
 */
@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final SimpleOrderService orders;

    /**
     * Метод поиска заказа по ID возвращает ResponseEntity<OrderDto>
     *
     * @param id int ID
     * @return ResponseEntity<OrderDTO>
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int id) {
        var order = orders.findOrderById(id);
        return new ResponseEntity<>(
                order.orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Order id: " + id + ", not found"
                        )
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Status> getStatusByOrderId(@PathVariable int id) {
        var status = orders.getStatusByOrderId(id);
        return new ResponseEntity<>(
                status.orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Status by order id: " + id + ", not found"
                        )
                ),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<OrderDTO> postCreate(@RequestBody OrderDTO orderDTO) {
        var orderSave = orders.create(orderDTO);
        return new ResponseEntity<>(
                orderSave.orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "The order has not been saved"
                        )
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody OrderDTO orderDTO) {
        var orderUpdate = orders.update(orderDTO);
        return orderUpdate ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        var orderDelete = orders.delete(id);
        return orderDelete ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }
}
