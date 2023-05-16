package ru.job4j.order.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.job4j.order.domain.Order;
import ru.job4j.order.domain.Status;

import javax.persistence.EntityManager;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j ORDER
 * OrderRepository TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 16.05.2023
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest()
class OrderRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private OrderRepository orderRepository;
    private Status status1;
    private Status status2;

    @BeforeEach
    public void initTest() {
        entityManager.createQuery("delete from Order").executeUpdate();
        entityManager.createQuery("delete from Status").executeUpdate();
        status1 = new Status(0, "status1");
        status2 = new Status(0, "status2");
        entityManager.persist(status1);
        entityManager.persist(status2);
    }

    @Test
    void whenSetStatusIdOrderByOrderIdThenDMLOneRowAndEqualsFindByID() {
        Order order = new Order(0,
                "OrderName",
                "OrderDescription",
                status1, -1);
        orderRepository.save(order);
        order.setStatus(status2);
        var rowEdit = orderRepository.setStatusByOrderId(order.getId(), order.getStatus().getId());
        var orderInDb = orderRepository.findById(order.getId());
        assertThat(rowEdit).isGreaterThan(0);
        assertThat(order).usingRecursiveComparison().isEqualTo(orderInDb.get());
    }

    @Test
    void whenSetStatusIdOrderByOrderIdThenDmlZeroRow() {
        Order order = new Order(0,
                "OrderName",
                "OrderDescription",
                status1, -1);
        orderRepository.save(order);
        var rowEdit = orderRepository.setStatusByOrderId(-1, order.getId());
        assertThat(rowEdit).isLessThan(1);

    }

    @Test
    void whenUpdateStatusOrderOfMethodSave() {
        Order order = new Order(0,
                "OrderName",
                "OrderDescription",
                status1, -1);
        orderRepository.save(order);
        order.setStatus(status2);
        orderRepository.save(order);
        var orderInDb = orderRepository.findById(order.getId());
        assertThat(order).usingRecursiveComparison().isEqualTo(orderInDb.get());
    }
}