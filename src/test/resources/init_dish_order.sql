DROP TABLE IF EXISTS Dishes_Orders;
CREATE TABLE Dishes_Orders (
  id       SMALLINT(6) NOT NULL,
  order_id SMALLINT(6) NOT NULL,
  dish_id  SMALLINT(6) NOT NULL,
  amount   TINYINT(4)  NOT NULL,
  sum      INT(11)     NOT NULL
);