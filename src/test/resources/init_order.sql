DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
  id        BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  user_id   BIGINT                                         NOT NULL,
  date_time TIMESTAMP                                      NOT NULL,
  total_sum BIGINT                                         NOT NULL,
  status    ENUM ('CREATED', 'CONFIRMED', 'READY', 'PAID') NOT NULL
);