--Создание таблицы ORDERS хранения заказов
CREATE TABLE orders
(
    id                SERIAL PRIMARY KEY,
    order_name        VARCHAR(2000),
    order_description VARCHAR(2000),
    status_id         INT REFERENCES statuses (id),
    dish_id           int
);