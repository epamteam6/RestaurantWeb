DROP TABLE IF EXISTS Dishes_Orders;
CREATE TABLE Dishes_Orders
(
  id       BIGINT(6) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  order_id BIGINT(6) NOT NULL,
  dish_id  BIGINT(6) NOT NULL,
  amount   BIGINT(4)  NOT NULL,
  item_sum BIGINT(11)     NOT NULL
);