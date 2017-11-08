-- phpMyAdmin SQL Dump
-- version 4.4.15.2
-- http://www.phpmyadmin.net
--
-- Хост: 10.0.2.10
-- Время создания: Окт 31 2017 г., 22:37
-- Версия сервера: 5.6.32-78.0-log
-- Версия PHP: 5.4.45

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `a208000_food`
--

-- --------------------------------------------------------

--
-- Структура таблицы `Dishes`
--

CREATE TABLE IF NOT EXISTS `Dishes` (
  `id`           BIGINT(6)   NOT NULL,
  `dishName`     VARCHAR(32) NOT NULL,
  `dish_type_id` BIGINT(6)   NOT NULL,
  `price`        BIGINT(6)   NOT NULL
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = cp1251;

--
-- Дамп данных таблицы `Dishes`
--

INSERT INTO `Dishes` (`id`, `dishName`, `dish_type_id`, `price`) VALUES
  (1, 'Borsch', 1, 150),
  (2, 'Kharcho', 1, 170),
  (3, 'Solyanka', 1, 200),
  (4, 'Olivie', 4, 180),
  (5, 'Greek', 4, 220),
  (6, 'Napoleon', 2, 135),
  (7, 'Medovik', 2, 145),
  (8, 'Ice Cream', 2, 110),
  (9, 'Sprite', 3, 55),
  (10, 'Milk', 3, 50),
  (11, 'Beer', 3, 120);

-- --------------------------------------------------------

--
-- Структура таблицы `Dishes_Orders`
--

CREATE TABLE IF NOT EXISTS `Dishes_Orders` (
  `id`       BIGINT(6)  NOT NULL,
  `order_id` BIGINT(6)  NOT NULL,
  `dish_id`  BIGINT(6)  NOT NULL,
  `amount`   INT(4)     NOT NULL,
  `price`    BIGINT(11) NOT NULL
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = cp1251;

--
-- Дамп данных таблицы `Dishes_Orders`
--

INSERT INTO `Dishes_Orders` (`id`, `order_id`, `dish_id`, `amount`, `price`) VALUES
  (1, 1, 2, 2, 340),
  (2, 1, 6, 1, 135),
  (3, 2, 4, 1, 180),
  (4, 2, 9, 2, 110),
  (5, 3, 8, 3, 330);

-- --------------------------------------------------------

--
-- Структура таблицы `Dish_Types`
--

CREATE TABLE IF NOT EXISTS `Dish_Types` (
  `id`        BIGINT(6)   NOT NULL,
  `dish_type` VARCHAR(32) NOT NULL
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = cp1251;

--
-- Дамп данных таблицы `Dish_Types`
--

INSERT INTO `Dish_Types` (`id`, `dish_type`) VALUES
  (1, 'Soups'),
  (2, 'Desserts'),
  (3, 'Drinks'),
  (4, 'Salads');

-- --------------------------------------------------------

--
-- Структура таблицы `Orders`
--

CREATE TABLE IF NOT EXISTS `Orders` (
  `id`        BIGINT(6)                                      NOT NULL,
  `user_id`   BIGINT(6)                                      NOT NULL,
  `date_time` TIMESTAMP                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `total_sum` BIGINT(11)                                     NOT NULL,
  `status`    ENUM ('CREATED', 'CONFIRMED', 'READY', 'PAID') NOT NULL
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = cp1251;

--
-- Дамп данных таблицы `Orders`
--

INSERT INTO `Orders` (`id`, `user_id`, `date_time`, `total_sum`, `status`) VALUES
  (1, 1, '2017-10-25 13:54:19', 475, 'CREATED'),
  (2, 2, '2017-10-25 13:54:19', 290, 'CONFIRMED'),
  (3, 4, '2017-10-25 13:54:19', 330, 'PAID');

-- --------------------------------------------------------

--
-- Структура таблицы `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `id`            BIGINT(6)   NOT NULL,
  `user_name`     VARCHAR(32) NOT NULL,
  `password_hash` VARCHAR(32) NOT NULL,
  `is_admin`      TINYINT(1)  NOT NULL DEFAULT '0'
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = cp1251;

--
-- Дамп данных таблицы `Users`
--

INSERT INTO `Users` (`id`, `user_name`, `password_hash`, `is_admin`) VALUES
  (1, 'Petrov', 'petrov', 0),
  (2, 'Ivanov', 'ivanov', 0),
  (3, 'Admin', 'admin', 1),
  (4, 'Rustam', 'rustam', 0),
  (5, 'Sergei', 'sergei', 0),
  (6, 'Yulia', 'yulia', 0);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `Dishes`
--
ALTER TABLE `Dishes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dish_type_id` (`dish_type_id`),
  ADD KEY `dish_type_id_2` (`dish_type_id`);

--
-- Индексы таблицы `Dishes_Orders`
--
ALTER TABLE `Dishes_Orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`, `dish_id`),
  ADD KEY `dish_id` (`dish_id`);

--
-- Индексы таблицы `Dish_Types`
--
ALTER TABLE `Dish_Types`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`);

--
-- Индексы таблицы `Orders`
--
ALTER TABLE `Orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Индексы таблицы `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `Dishes`
--
ALTER TABLE `Dishes`
  MODIFY `id` BIGINT(6) NOT NULL AUTO_INCREMENT,
  AUTO_INCREMENT = 13;
--
-- AUTO_INCREMENT для таблицы `Dishes_Orders`
--
ALTER TABLE `Dishes_Orders`
  MODIFY `id` BIGINT(6) NOT NULL AUTO_INCREMENT,
  AUTO_INCREMENT = 6;
--
-- AUTO_INCREMENT для таблицы `Dish_Types`
--
ALTER TABLE `Dish_Types`
  MODIFY `id` BIGINT(6) NOT NULL AUTO_INCREMENT,
  AUTO_INCREMENT = 5;
--
-- AUTO_INCREMENT для таблицы `Orders`
--
ALTER TABLE `Orders`
  MODIFY `id` BIGINT(6) NOT NULL AUTO_INCREMENT,
  AUTO_INCREMENT = 4;
--
-- AUTO_INCREMENT для таблицы `Users`
--
ALTER TABLE `Users`
  MODIFY `id` BIGINT(6) NOT NULL AUTO_INCREMENT,
  AUTO_INCREMENT = 7;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `Dishes`
--
ALTER TABLE `Dishes`
  ADD CONSTRAINT `Dishes_ibfk_1` FOREIGN KEY (`dish_type_id`) REFERENCES `Dish_Types` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `Dishes_Orders`
--
ALTER TABLE `Dishes_Orders`
  ADD CONSTRAINT `Dishes_Orders_ibfk_1` FOREIGN KEY (`dish_id`) REFERENCES `Dishes` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  ADD CONSTRAINT `Dishes_Orders_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `Orders` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `Orders`
--
ALTER TABLE `Orders`
  ADD CONSTRAINT `Orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
