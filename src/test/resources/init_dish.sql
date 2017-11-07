DROP TABLE IF EXISTS Dishes;
CREATE TABLE Dishes
(
  id           BIGINT AUTO_INCREMENT PRIMARY KEY,
  dishname         VARCHAR(32) NOT NULL,
  dish_type_id BIGINT(6)   NOT NULL,
  price        BIGINT(6)   NOT NULL
);
