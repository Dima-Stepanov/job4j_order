--Заполнение таблицы Orders тестовыми данными
INSERT INTO orders(order_name, order_description, status_id, dish_id)
VALUES ('Хочу шаурму', 'Оплата наличными выйду заберу',
        (SELECT s.id FROM statuses AS s WHERE s.status_name = 'Создан'),
        1);