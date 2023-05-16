package ru.job4j.order.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.order.domain.Order;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j ORDER
 * OrderRepository хранилище заказов в базе данных.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 27.04.2023
 */
public interface OrderRepository extends CrudRepository<Order, Integer> {
    /**
     * Метод обновляет Status ID в модели Order ID
     *
     * @param orderId  Order ID
     * @param statusId Status ID
     * @return int dml row edit
     */
    @Transactional
    @Modifying
    @Query("UPDATE Order AS o SET o.status.id =:statusId WHERE o.id =:orderId")
    int setStatusByOrderId(@Param("orderId") int orderId, @Param("statusId") int statusId);
}
