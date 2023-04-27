--Создание таблицы для хранения моделей блюд DISH
CREATE TABLE STATUSES
(
    id          SERIAL PRIMARY KEY,
    status_name VARCHAR(2000) NOT NULL UNIQUE
);